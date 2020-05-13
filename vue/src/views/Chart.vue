<script>
import { Line } from 'vue-chartjs'

export default {
  name: 'Chart',
  extends: Line,
  data () {
    return {
      data: {
        // 年齢
        labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25'],
        datasets: [
          {
            // スコア
            data: [0, 30, 30, 40, -20, 50, 50, 60, 70, 0,
              0, 2, 20, 40, 22, 0, 8, 0, 2, 2,
              70, 70, 70, 70, 0],
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
    this.renderChart(this.data, this.options)
  }
}
</script>
