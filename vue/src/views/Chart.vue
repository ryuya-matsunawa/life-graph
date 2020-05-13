<script>
import { Line } from 'vue-chartjs'

export default {
  name: 'Chart',
  extends: Line,
  data () {
    return {
      data: {
        labels: ['0', '5', '10', '15', '20', '25', '30'],
        datasets: [
          {
            data: [0, 30, 30, 40, -20, 50, 70],
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
        // グラフ軸の詳細
        scales: {
          // x軸
          xAxes: [{
            scaleLabel: {
              display: true,
              labelString: '年齢'
            }
          }],
          // y軸
          yAxes: [{
            scaleLabel: {
              labelString: ''
            },
            ticks: {
              // 軸の最大値
              suggestedMax: 100,
              // 軸の最小値
              suggestedMin: -100,
              // 軸の刻み幅
              stepSize: 10
            }
          }],
          legend: {
            display: false
          }
        }
      }
    }
  },
  mounted () {
    this.renderChart(this.data, this.options)
  }
}
</script>
