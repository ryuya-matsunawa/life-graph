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
        // hover時にでるやつにどんなオプションつけられるか
        // hover時のデザイン変える時はtooltips内で！このサイト見たらだいたいいけると思う
        // https://tr.you84815.space/chartjs/configuration/tooltip.html
        tooltips: {
          mode: 'point',
          callbacks: {
            // タイトルの後に表示するテキストを返す
            afterTitle: function (data) {
              var titleText = ['歳']
              return titleText
            },
            // ツールチップ内の各項目に表示するテキストを返す
            // label: function (data) {
            //   var commentText = this.$store.state.lifeGraph[2]
            //   var lifeScore = this.$store.state.lifeGraph[1]
            //   for (var i = 0; i < this.$store.state.lifeGraph[2].length; i++) {
            //     if (lifeScore[i] !== null) {
            //       return commentText[i]
            //     }
            //   }
            // }
            label: function (data) {
              // var commentText =
              // // this.$store.state.chart.lifeGraph[2]
              // ['コメント１', 'コメント２', 'コメント3', 'コメント4', 'コメント5', 'コメント6', 'コメント7', 'コメント8', 'コメント9', 'コメント10']
              // return commentText
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
    this.getComments()
    this.renderChart(this.data, this.options)
    // console.log(this.$store.state.lifeGraph[2])
  },
  methods: {
    setLabels () {
      const age = this.$store.state.chart.lifeGraph.age
      this.data.labels = age
    },
    setData () {
      const lifeScores = this.$store.state.chart.lifeGraph.scores
      this.data.datasets[0].data = lifeScores
    },
    getComments () {
      const comment = this.$store.state.chart.lifeGraph[2]
      this.options.tooltips.callbacks.label = function (data) {
        // var commentText = this.$store.state.chart.lifeGraph[2]
        //   var commentText = this.$store.state.lifeGraph[2]
        //   var lifeScore = this.$store.state.lifeGraph[1]
        //   for (var i = 0; i < this.$store.state.lifeGraph[2].length; i++) {
        //     if (lifeScore[i] !== null) {
        //       return commentText[i]
        //     }
        return comment
      }
    }
  }
}
</script>
