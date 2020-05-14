<script>
import { Line } from 'vue-chartjs'
// import { mapMutations } from 'vuex'

export default {
  name: 'Chart',
  extends: Line,
  data () {
    return {
      data: {
        // 年齢
        labels: [],
        datasets: [
          {
            // スコア
            data: [],
            borderColor: '#CFD8DC',
            // 線の中に色つけるかどうか
            fill: false,
            type: 'line',
            // 線の曲がり方が柔らかくなる
            lineTension: 0.2
          }
        ]
      },
      // チャート図の詳細設定
      options: {
        // hover時に
        tooltips: {
          mode: 'point',
          callbacks: {
            afterBody: function (data) {
              var multistringText = ['コメント１', 'コメント２', 'コメント3', 'コメント4']
              return multistringText
            }
          }
        },
        // スコアに欠損(null)があっても線が繋がるようにしてる
        spanGaps: true,
        // ボタンみたいなやつ消してる
        legend: {
          display: false
        },
        scales: {
          // x軸
          xAxes: [{
            // 34-37で背景の縦線を消す
            stacked: false,
            gridLines: {
              display: false
            },
            scaleLabel: {
              display: true
            }
          }],
          // y軸
          yAxes: [{
            stacked: false,
            gridLines: {
              display: false
            },
            ticks: {
              // 軸の最大値
              suggestedMax: 100,
              // 軸の最小値
              suggestedMin: -100,
              // 軸の刻み幅
              stepSize: 10
            }
          }]
        }
      }
    }
  },
  mounted () {
    this.setLabels()
    this.setData()
    this.renderChart(this.data, this.options)
  },
  methods: {
    setLabels () {
      // こっちはなんかうまくいかないw
      // const age = this.$store.state.chart.age
      const age = this.$store.state.chart.age
      this.data.labels = age
    },
    setData () {
      const lifeScores = this.$store.state.chart.lifeScores
      this.data.datasets[0].data = lifeScores
    }
  }
  // mounted () {
  //   this.getAge()
  //   this.getLifeScores()
  //   this.renderChart(this.data, this.options)
  // },
  // methods: {
  //   getAge () {
  //     const age = this.$store.state.age
  //     this.data.labels = age
  //   },
  //   getLifeScores () {
  //     const lifeScores = this.$store.state.lifeScores
  //     this.data.datasets[0].data = lifeScores
  //   }
  // }
}
</script>
