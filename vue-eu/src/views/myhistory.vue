<template>
  <div class="right-down">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <div class="goods" v-for="(item, index) in hgoodsList"  @mouseenter="changeLove(index)" @mouseleave="changeLove()" style="background-color: white">
        <el-image @click="toGoodsDetail(item.gid)" class="product-img" :src="item.pics" />
        <div class="product-gname">
          <a>{{item.gname}}</a>
        </div>
        <i :class="item.isCollect == 1 ? 'collectBtn2' : 'collectBtn'"
           :style="currentLove == index ? {opacity: 1} : {display: ''}" title="收藏"
           @click="addUpdateLove(item,index)"
        >收藏</i>
      </div>
    </el-tabs>
    <div style="margin-top: 60px;position: relative;top: -20px">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="historytotal"
        :page-size = "pageSize"
        @current-change="CurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
    import axios from "axios";

    export default {
      name: "myhistory",
      data(){
        return{
          hgoodsList: [], //历史商品集合
          historytotal:0, //足迹条数
          activeName: '',
          currentLove: -1,
          pageSize: 8,  //页面最大数据条数
          goodsList: [], //商品集合
          currentPage: 1,
        }
      },
      mounted() {
        axios.get("/eu/findUserByMember",{params:
            {mid: localStorage.getItem("mid")}
        }).then(res=>{
          this.member = res.data.data
        })
        this.findHistoryList();
      },
      methods:{
        CurrentChange(val) {
          this.currentPage = val;
          this.findHistoryList();
        },
        handleClick(tab, event) {
          console.log(tab, event);
        },
        toaddLoveGoods(item, index){
          axios.get("/eu/addLoveGoods",{params:{mid:localStorage.getItem("mid"),gid:item.gid}
          }).then(res=>{
            if(res.data.code == 200){
              this.hgoodsList[index].isCollect = 1
            }
          })
        },
        todelLoveGoods(item, index){
          axios.get("/eu/delLoveGoods",{params:{mid:localStorage.getItem("mid"),gid:item.gid}
          }).then(res=>{
            if(res.data.code == 200){
              this.hgoodsList[index].isCollect = 0
            }
          })
        },
        changeLove(index){
          if(index != undefined){
            this.currentLove = index
          }else{
            this.currentLove = -1
          }
        },
        addUpdateLove(item, index){
          console.log(item)
          console.log(index)

          if(item.isCollect == 1){
            this.todelLoveGoods(item, index)
          }else{
            this.toaddLoveGoods(item, index)
          }
        },
        toGoodsDetail(gid){
          if(this.member == null){
            alert("请先登录！")
            this.$router.push("/login")
          }
          const { href } = this.$router.resolve({
            //path是要跳转到的页面路径
            path: "/detail",
            query: {gid: gid}
          });
          //结合open打开新的页面
          window.open(href, "_blank");
        },
        logout(){
          localStorage.removeItem("mid");
          this.$router.push("/login")
        },
        findHistoryList(){
          let page = (this.currentPage - 1) * (this.pageSize);
          axios.get("/eu/findHistory", {params:{page: page, rows: this.pageSize, mid: localStorage.getItem("mid")}
          }).then(res=>{
            this.hgoodsList = res.data.goodsInfoList;
            this.historytotal = res.data.historytotal;
          })
        }
      }
    }
</script>

<style scoped>
  .el-tab-pane{
    width: 1300px;
  }
  .el-tabs{
    height: 625px;
  }
  .el-pagination{
    text-align: center;
  }
  .collectBtn{
    opacity: 0;
    position: absolute;
    width: 22px;
    height: 22px;
    margin: -265px 0 0 210px;
    background: url(../../static/img/shouc1.png) no-repeat center;
    z-index: 1;
    font-size: 0;
    background-size: 100% 100% !important;
    cursor: pointer;
  }
  .collectBtn2{
    opacity: 0;
    position: absolute;
    width: 22px;
    height: 22px;
    margin: -265px 0 0 210px;
    background: url(../../static/img/shouc2.png) no-repeat center;
    z-index: 1;
    font-size: 0;
    background-size: 100% 100% !important;
    cursor: pointer;
  }
  .el-select-dropdown{
  }
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
  .demonstration {
    display: block;
    color: #8492a6;
    font-size: 14px;
    margin-bottom: 20px;
  }
  .goods{
    border-radius: 4px 4px 0 0;
    width: 252.8px;
    height: 230px;
    background-color: #ffffff;
    list-style: none;
    display: inline-block;
    margin-top:30px;
    margin-left: 40px
  }
  .product-img{
    width: 100%;
    height: 250px;
    border-radius: 4px 4px 0 0;
  }
  .product-gname{
    position: relative;
    top: -5px;
    width: 252.8px;
    height: 35px;
    background-color: white;
    border-radius: 0 0 4px 4px ;

  }
  .product-gname a{
    font-size: 14px;
    height: 14px;
    line-height: 14px;
    color: #303030;
    position: relative;
    right: -10px;
    top: 5px;
  }
</style>
