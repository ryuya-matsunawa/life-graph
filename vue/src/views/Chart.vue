<script>
import { Line } from 'vue-chartjs'

export default {
  name: 'Chart',
  extends: Line,
  props: {
    userid: {
      type: Number,
      default: null
    }
  },
  data () {
    return {
      data: {
        // 年齢
        labels: [],
        datasets: [
          {
            // スコア
            data: [],
            borderColor: 'gray',
            // 線の中に色つけるかどうか
            fill: false,
            type: 'line',
            // 線の曲がり方が柔らかくなる
            lineTension: 0.2
          }
        ]
      },
      options: {
        // ツールチップで見た目変えるときはこのサイトに変え方諸々書いてるから見てみて！
        // https://misc.0o0o.org/chartjs-doc-ja/configuration/tooltip.html#%E3%83%84%E3%83%BC%E3%83%AB%E3%83%81%E3%83%83%E3%83%97
        tooltips: {
          // デフォルトであると思われるホバーを出すか出さないか
          enabled: false,
          // デフォルトのホバーじゃなくて自分で好きにホバーイベントを作る
          custom: []
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
              display: true
            },
            ticks: {
              // 軸の最大値
              suggestedMax: 100,
              // 軸の最小値
              suggestedMin: -100,
              // 軸の刻み幅
              stepSize: 20
            }
          }]
        }
      }
    }
  },
  async mounted () {
    await this.$store.dispatch('chart/fetchGraph', this.userid)
    this.setLabels()
    this.setData()
    this.setComments()
    this.renderChart(this.data, this.options)
  },
  methods: {
    setLabels () {
      this.data.labels = this.$store.state.chart.contents.map((content) => {
        return content.age
      })
    },
    setData () {
      this.data.datasets[0].data = this.$store.state.chart.contents.map((content) => {
        return content.score
      })
    },
    setComments () {
      const comment = []
      this.$store.state.chart.contents.map((content) => {
        comment.push(content.comment)
      })
      this.options.tooltips.custom = function (tooltipModel) {
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
        function getBody (bodyItem) {
          return bodyItem.lines
        }
        // テキストを設定する
        if (tooltipModel.body) {
          var titleLines = tooltipModel.title
          var com = comment
          var bodyLines = tooltipModel.body.map(getBody)
          var innerHtml = '<thead>'
          // x軸の値を返してくれる
          titleLines.forEach(function (age) {
            var comNum = age - 1
            // '<tr><th>'は多分太字にするやつ
            innerHtml += '<tr><th>' + 'Age ' + age + '</th></tr>'
            // innerHtml += '</thead><tbody>'
            bodyLines.forEach(function (body, i) {
              var colors = tooltipModel.labelColors[i]
              var style = 'background:' + colors.backgroundColor
              style += '; border-color:' + colors.borderColor
              style += '; border-width: 1px'
              var span = '<span style="' + style + '"></span>'
              // '<tr><td>' は多分改行
              if (com[comNum] === null || com[comNum] === undefined) {
                innerHtml += '<tr><td>' + span + 'score：' + body + '</td></tr>'
              } else {
                innerHtml += '<tr><td>' + span + 'score：' + body + '</td></tr>' + 'reason：' + com[comNum]
              }
            })
          })
          innerHtml += '</tbody>'
          var tableRoot = tooltipEl.querySelector('table')
          tableRoot.innerHTML = innerHtml
        }
        // `this` はツールチップ全体
        var position = this._chart.canvas.getBoundingClientRect()
        // 表示、配置、およびフォントスタイルの設定
        tooltipEl.style.opacity = 0.8
        tooltipEl.style.position = 'absolute'
        tooltipEl.style.left = position.left + window.pageXOffset + tooltipModel.caretX + 'px'
        tooltipEl.style.top = position.top + window.pageYOffset + tooltipModel.caretY + 'px'
        tooltipEl.style.fontFamily = 'Tahoma'
        tooltipEl.style.fontSize = '18px'
        tooltipEl.style.fontStyle = 'Tahoma'
        tooltipEl.style.pointerEvents = 'none'
        tooltipEl.style.backgroundColor = 'rgb(255,255,153)'
        tooltipEl.style.color = 'black'
        tooltipEl.style.padding = '10px'
        tooltipEl.style.width = '250px'
        tooltipEl.style.textAlign = 'left'
        tooltipEl.style.wordBreak = 'normal'
      }
    }
  }
}
</script>
