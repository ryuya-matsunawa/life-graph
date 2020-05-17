package com.lifegraph.team20.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.Account;
import com.lifegraph.team20.models.ERole;
import com.lifegraph.team20.models.Role;
import com.lifegraph.team20.models.User;
import com.lifegraph.team20.models.UserData;
import com.lifegraph.team20.payload.request.LoginRequest;
import com.lifegraph.team20.payload.request.LogoutRequest;
import com.lifegraph.team20.payload.request.SignupRequest;
import com.lifegraph.team20.payload.response.JwtResponse;
import com.lifegraph.team20.payload.response.MessageResponse;
import com.lifegraph.team20.repository.RoleRepository;
import com.lifegraph.team20.repository.UserRepository;
import com.lifegraph.team20.security.jwt.JwtUtils;
import com.lifegraph.team20.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController extends HttpServlet {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	// http://localhost8080/auth/login が叩かれる
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		// トークンが有効期限付きで作られる
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(),
												 userDetails.getUsername(),
												 userDetails.getEmail(),
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> sampleUser(@Valid @RequestBody LogoutRequest logoutRequest , HttpServletRequest req) {

		String jwt = parseJwt(req);

		if (jwtUtils.validateJwtToken(jwt)) {
			// 有効期限内の場合
			// ブラックリストにいれる処理
		}

		return ResponseEntity.ok("OK");
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	  public List<Account> account() {
		List<Account> account = selectAccount();
		return account;
	  }

	private List<Account> selectAccount() {
		final String sql = "select * from users inner join user_roles on users.id = user_roles.user_id\n" +
				"inner join roles on roles.id = user_roles.`role_id`;";
		return jdbcTemplate.query(sql, new RowMapper<Account>() {
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException{
				return new Account(rs.getInt("id"), rs.getString("username"), rs.getString("name"));
			}
		});
	}

	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public List<UserData> userData() {
		List<UserData> userDatas = setUserData();
		return userDatas;
	}

	private List<UserData> setUserData() {
		final String sql = "select * from users inner join parent_graphs on users.id = parent_graphs.user_id";
		return jdbcTemplate.query(sql, new RowMapper<UserData>() {
			public UserData mapRow(ResultSet rs, int rowNum) throws SQLException{
				return new UserData(rs.getInt("id"), rs.getString("username"), rs.getTimestamp("updated_at"));
			}
		});
	}

//	@GetMapping("/accounts/{id}")
//	public ResponseEntity<?> authenticateUser(@Valid @RequestBody AccountRequest accountRequest) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(accountRequest.getUsername(), accountRequest.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String jwt = jwtUtils.generateJwtToken(authentication);
//
//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//		List<String> roles = userDetails.getAuthorities().stream()
//				.map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//
//		return ResponseEntity.ok(new JwtResponse(jwt,
//												 userDetails.getId(),
//												 userDetails.getUsername(),
//												 userDetails.getEmail(),
//												 roles));
//	}
}
