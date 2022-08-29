<template>
  <!-- 顶部快捷导航start -->
  <div>
    <Head></Head>
  <!-- nav start -->
  <!-- nav end -->

  <!-- 详情页内容部分	 -->
    <div class="de_container w">
      <div>
        <div class="detail-gname">
          <a>{{goodsInfo.gname}}</a>
        </div>
        <div class="choose_btns">
          <a href="#" class="addcar"
             :class="goodsInfo.isCollect == 1 ? 'collectBtn2' : 'collectBtn'"
             @click="addUpdateLove()">收藏
          </a>
        </div>
        <div class="detail-wrap">
          <el-image class="detail-img" :src="goodsInfo.pics"></el-image>
        </div>
        <div style="padding-top: 130px;padding-left: 150px">
          <el-date-picker
            v-model="time"
            type="date"
            placeholder="选择日期"
            @change="loadData()"
            value-format="yyyyMMdd"
          >
          </el-date-picker>

          <el-select v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.aaid"
              :label="item | filterItem"
              :value="item.aaid"
            >
            </el-option>
          </el-select>

          <el-button type="success" style="width: 120px;height: 50px" :disabled="buttonDis" @click="toaddOrder()">预定</el-button>
        </div>

        <div class="details" v-html="goodsInfo.detail"></div>
      </div>
    </div>
    <div>
      <foot></foot>
    </div>
  </div>

</template>

<script>
    import axios from "axios"

    export default {
      name: "Detail",
      data(){
          return{
            goodsInfo: null,
            typeRed: 'typeRed',
            active : 'active',
            currentLove: -1,
            time: '',
            value: null,
            options: null,
            buttonDis: true
          }
      },
      filters:{
        filterItem(item){
          return " " + item.starttime + " - " + item.endtime;
        }
      },
      watch:{
        value(val){
          if(val != null){
            this.buttonDis = false
          }else{
            this.buttonDis = true
          }
        }
      },
      mounted() {
        this.addHistory();
        this.tofindGoodsDetail();
        setTimeout(()=>{
          axios.get("/eu/addHistory",{
            params:{mid: localStorage.getItem("mid"),gid: this.$route.query.gid}
          }).then(res=>{
            console.log(res.data)
          })
        },3000)
      },
      methods:{
        addUpdateLove(){

          if(this.goodsInfo.isCollect == 1){
            this.todelLoveGoods()
          }else{
            this.toaddLoveGoods()
          }
        },
        toaddLoveGoods(){
          axios.get("/eu/addLoveGoods",{params:{mid:localStorage.getItem("mid"),gid:this.goodsInfo.gid}
          }).then(res=>{
            if(res.data.code == 200){
              this.goodsInfo.isCollect = 1
            }
          })
        },
        toaddOrder(){
          axios.get("/eu/addOrder",{params:{
            mid:localStorage.getItem("mid"),gid:this.goodsInfo.gid, aaid: this.value}
          }).then(res=>{
            if(res.data.code == 200){
              this.value = null
              this.time = null
              alert("预约成功！")
            }
          })
        },
        todelLoveGoods(){
          axios.get("/eu/delLoveGoods",{params:{mid:localStorage.getItem("mid"),gid:this.goodsInfo.gid}
          }).then(res=>{
            if(res.data.code == 200){
              this.goodsInfo.isCollect = 0
            }
          })
        },
        tofindGoodsDetail(){
          axios.get("/eu/findGoodsDetail",{params:
              {gid: this.$route.query.gid, mid: localStorage.getItem("mid")}
          }).then(res=>{
            console.log(res.data)
            this.goodsInfo = res.data
          })
        },
        addHistory(){
          console.log("调用了此方法！")
        },
        loadData(){
          this.options = null;
          this.value = null;
          axios.get("/eu/selectAddInfo", {params:{time: this.time, gid: this.$route.query.gid}
          }).then(res=>{
            this.options = res.data
          })
        },
      }
    }
</script>

<style scoped>
  @import "../assets/css/base.css";
  @import "../assets/css/common.css";
  @import "../assets/css/detail.css";
  .collectBtn{
    position: absolute;
    width: 40px;
    height: 40px;
    background: url(../../static/img/shouc1.png) no-repeat center;
    z-index: 1;
    font-size: 0;
    background-size: 100% 100% !important;
    cursor: pointer;
    margin: 5px 0 0 60px;
    background-color: pink;
  }

  .collectBtn2{
    position: absolute;
    width: 40px;
    height: 40px;
    background: url(../../static/img/shouc2.png) no-repeat center;
    z-index: 1;
    font-size: 0;
    background-size: 100% 100% !important;
    cursor: pointer;
    margin: 5px 0 0 60px;
    background-color: pink;
  }
  .detail-img{
    width: 800px;
    height: 450px;
    margin-top: 100px;
  }
  .detail-wrap{
    width: 900px;
    height: 450px;
    margin: auto;
  }
  .details{
    margin-left: 100px;
    width: 900px;
    margin-top: 130px;
  }
  .detail-gname {
    position: absolute;
    margin-top: 40px;
    left: 350px;
  }
  .detail-gname a{
    font-size: 30px;
  }

</style>
