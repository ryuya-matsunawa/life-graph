<script>
import { Line } from 'vue-chartjs'

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
      options: {
        tooltips: {
          // デフォルトであると思われるホバーを出すか出さないか
          enabled: false,
          // デフォルトのホバーじゃなくて自分で好きにホバーイベントを作る
          custom: function (tooltipModel) {
            // ツールチップ要素
            var tooltipEl = document.getElementById('chartjs-tooltip')
            // ホバーを出す（最初のレンダリング時に要素を作成する）
            if (!tooltipEl) {
              tooltipEl = document.createElement('div')
              tooltipEl.id = 'chartjs-tooltip'
              tooltipEl.innerHTML = '<table></table>'
              document.body.appendChild(tooltipEl)
            }
            // ツールチップがない場合は非表示にします
            // なんかカーソルを外した後はホバーイベントが消えるようになる
            if (tooltipModel.opacity === 0) {
              tooltipEl.style.opacity = 0
              return
            }
            // キャレット(ツールチップが指し示すもの)の位置を設定する
            // なんかカーソルを外した後ホバーイベントが消えるようになる
            // tooltipEl.classList.remove('above', 'below', 'no-transform')
            // if (tooltipModel.yAlign) {
            //   tooltipEl.classList.add(tooltipModel.yAlign)
            // } else {
            //   tooltipEl.classList.add('no-transform')
            // }
            function getBody (bodyItem) {
              return bodyItem.lines
            }
            // テキストを設定する
            if (tooltipModel.body) {
              var titleLines = tooltipModel.title
              var com = ['コメント１', 'コメント２', 'コメント3', 'コメント4', 'コメント5', 'コメント6', 'コメント7', 'コメント8', 'コメント9', 'コメント10']
              var bodyLines = tooltipModel.body.map(getBody)
              var innerHtml = '<thead>'
              // x軸の値を返してくれる
              titleLines.forEach(function (age) {
                var comNum = age - 1
                innerHtml += '<tr><th>' + age + '歳のとき' + '</th></tr>'
                // innerHtml += '</thead><tbody>'
                bodyLines.forEach(function (body, i) {
                  var colors = tooltipModel.labelColors[i]
                  var style = 'background:' + colors.backgroundColor
                  style += '; border-color:' + colors.borderColor
                  style += '; border-width: 2px'
                  var span = '<span style="' + style + '"></span>'
                  innerHtml += '<tr><td>' + span + '満足度：' + body + ' ポイント' + '</td></tr>' + '内容：' + com[comNum]
                })
              })
              innerHtml += '</tbody>'
              var tableRoot = tooltipEl.querySelector('table')
              tableRoot.innerHTML = innerHtml
            }
            // `this` はツールチップ全体
            var position = this._chart.canvas.getBoundingClientRect()
            // 表示、配置、およびフォントスタイルの設定
            tooltipEl.style.opacity = 1
            tooltipEl.style.position = 'absolute'
            tooltipEl.style.left = position.left + window.pageXOffset + tooltipModel.caretX + 'px'
            tooltipEl.style.top = position.top + window.pageYOffset + tooltipModel.caretY + 'px'
            tooltipEl.style.fontFamily = tooltipModel._bodyFontFamily
            tooltipEl.style.fontSize = tooltipModel.bodyFontSize + 'px'
            tooltipEl.style.fontStyle = tooltipModel._bodyFontStyle
            tooltipEl.style.padding = tooltipModel.yPadding + 'px ' + tooltipModel.xPadding + 'px'
            tooltipEl.style.pointerEvents = 'none'
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
            // 背景の縦線を消す
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
            // 背景の横線を消す
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
    // this.setComments()
    this.renderChart(this.data, this.options)
  },
  methods: {
    setLabels () {
      const ages = []
      this.$store.state.chart.contents.forEach((content) => {
        ages.push(content.age)
      })
      this.data.labels = ages
    },
    setData () {
      const lifeScores = []
      this.$store.state.chart.contents.forEach((content) => {
        lifeScores.push(content.lifeScores)
      })
      this.data.datasets[0].data = lifeScores
    }
    // setCommnets () {
    //   const comment = []
    //   this.$store.state.chart.contents.forEach((content) => {
    //     comment.push(content.comment)
    //   })
    //   this.options.tooltips.callbacks.label = comment
    // }
  }
}
</script>
