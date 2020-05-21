module.exports = {
  extends: 'lint-config-standard',
  plugins: ['stylelint-order', 'stylelint-scss'],
  rules: {
    'at-rule-no-unknown': null,
    'scss/at-rule-no-unknown': true,
    'order/properties-alphabetical-order': true,
    'no-empty-source': null,
    'font-family-no-missing-generic-family-keyword': 0,
    'rule-empty-line-before': [
      'always',
      {
        except: 'inside-block'
      }
    ]
  }
}
