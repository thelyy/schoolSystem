<template>
  <div style="width: auto">
    <div style="background-color: #f7f7f7 !important;">
      <Head></Head>
      <div style="margin-top: 50px">
        <el-image :src="headImg" style="width: 100%;height: 500px"></el-image>
      </div>
      <div class="zhongjian">
        <div class="zhongjian-active">
          <el-input class="search-input" type="text" autocomplete="off" v-model="keyword" />
          <el-button class="search-button" type="primary" icon="el-icon-search"
          @click="findGoodsBySearch()"
          >搜方案</el-button>
        </div>
        <div class="hot-content-active">
          <dl class="fix2">
            <dt style="height: 40px;width: 80px;float:left;color: #909090">场地类型:</dt>
            <dd class="styleLi-2">
              <span
                v-for="(address, index) in goodsAddress"
                :class="index == currentIndex1 ? typeRed : active"
              @click="findGoodsByTA('aname', index)"
              >{{address.aname}}</span>
            </dd>
          </dl>
          <dl class="fix3">
            <dt style="height: 40px;width: 80px;float:left;color: #909090">类型:</dt>
            <dd class="styleLi-3">
              <span
                v-for="(type, index) in goodsType"
                :class="index == currentIndex2 ? typeRed : active"
                @click="findGoodsByTA('tname', index)"
              >{{type.tname}}</span>
            </dd>
          </dl>
        </div>

        <div id="flash">
          <ul id="plays" >
            <li style="display: block;"><img width="1100px" height="300px"  src="static/img/11.jpg"></li>
            <li><img width="1100px" height="300px" src="static/img/04.jpg" ></li>
            <li><img width="1100px" height="300px" src="static/img/01.jpg" ></li>

          </ul>
          <ul id="button">
            <li><div style="background: #A10000;"></div></li>
            <li><div></div></li>
            <li><div></div></li>
          </ul>
        </div>
        <h2 class="index-h1">精选方案</h2>
      </div>

      <div class="index-product">
        <div class="product">
          <div class="goods" v-for="(item, index) in goodsList"  @mouseenter="changeLove(index)" @mouseleave="changeLove()" style="background-color: white">
            <el-image @click="toGoodsDetail(item.gid)" class="product-img" :src="item.pics"/>
            <div class="product-gname">
              <a>{{item.gname}}</a>
            </div>
            <i :class="item.isCollect == 1 ? 'collectBtn2' : 'collectBtn'"
               :style="currentLove == index ? {opacity: 1} : {display: ''}" title="收藏"
               @click="addUpdateLove(item,index)"
            >收藏</i>
          </div>

          <div style="margin-top: 60px;position: relative;top: -20px">
            <el-pagination
              background
              layout="prev, pager, next"
              :total="total"
              :page-size = "pageSize"
              @current-change="CurrentChange"
            >
            </el-pagination>
          </div>

        </div>
      </div>

    </div>
    <foot style="margin-top: -500px"></foot>

  </div>

</template>

<script>
    import {banner} from '../../static/js/rotation'
    import axios from "axios"

    export default {
      name: "index",
      data() {
        return {
          showCode:false,
          goodsList: [], //商品集合
          total:0, //数据总条数
          pageSize: 8,  //页面最大数据条数
          goodsType: [],
          goodsAddress: [],
          currentIndex1: -1,
          currentIndex2: -1,
          typeRed: 'typeRed',
          active : 'active',
          keyword: '',
          headImg: '/static/img/09.jpg',
          currentLove: -1
        }
      },
      mounted() {
        axios.get("/eu/findUserByMember",{params:
            {mid: localStorage.getItem("mid")}
        }).then(res=>{
          this.member = res.data.data
        })


        axios.get("/eu/selectTname", {params:{}
        }).then(res=>{
          this.goodsType = res.data
        })

        axios.get("/eu/selectAname",{params:{}
        }).then(res=>{
          this.goodsAddress = res.data;
        })

        axios.get("/eu/findByPage", {params:{mid: localStorage.getItem("mid")}
        }).then(res=>{
          this.goodsList = res.data;
          this.total = res.data.total;
          this.goodsList = res.data.goodsInfoList;

        })
        banner();


      },
      methods:{
        selectGoods : function () {

        },
        CurrentChange(val) {
          let page = (val - 1) * (this.pageSize);
          axios.get("/eu/findByPage", {params:{page: page, rows: this.pageSize, mid: localStorage.getItem("mid")}
          }).then(res=>{
            this.goodsList = res.data.goodsInfoList
          })
        },
        findGoodsByTA(name, index){
          if(name == 'aname'){
            this.currentIndex1 = index
          }
          if(name == 'tname'){
            this.currentIndex2 = index
          }

          var aname;
          var tname;

          if(this.currentIndex1 < 0){
            aname = null
          }else{
            aname = this.goodsAddress[this.currentIndex1].aname;
          }

          if(this.currentIndex2 < 0){
            tname = null
          }else{
            tname = this.goodsType[this.currentIndex2].tname;
          }

          axios.get("/eu/findGoodsByTA", {params: {
              aname:aname, tname:tname}
          }).then(res=>{
            this.total = res.data.total;
            this.goodsList = res.data.goodsInfoList
          })
        },

        findGoodsBySearch() {
          axios.get("/eu/findGoodsBySearch",{params:{
            gname : this.keyword}
          }).then(res=>{
            this.total = res.data.total;
            this.goodsList = res.data.goodsInfoList
          })
        },
        toGoodsDetail(gid){
          if(this.member == null){
            alert("请先登录！")
            this.$router.push("/login")
          } else {
            const { href } = this.$router.resolve({
              //path是要跳转到的页面路径
              path: "/detail",
              query: {gid: gid}
            });
            //结合open打开新的页面
            window.open(href, "_blank");
          }
        },
        logout(){
          localStorage.removeItem("mid");
          this.$router.push("/login")
        },
        toaddLoveGoods(item, index){
          axios.get("/eu/addLoveGoods",{params:{mid:localStorage.getItem("mid"),gid:item.gid}
          }).then(res=>{
            if(res.data.code == 200){
              this.goodsList[index].isCollect = 1
            }
          })
        },
        todelLoveGoods(item, index){
          axios.get("/eu/delLoveGoods",{params:{mid:localStorage.getItem("mid"),gid:item.gid}
          }).then(res=>{
            if(res.data.code == 200){
              this.goodsList[index].isCollect = 0
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
        }
      }
    }


</script>

<style scoped>
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
  .active{
    font-size: 14px;

  }

  .typeRed{
    color: #de002d;
    font-weight: bold;
  }

  .active:hover{
    color: #de002d;
    font-weight: bold;
  }


  .el-pagination{
    text-align: center;
  }
  .zhongjian{
    width: 100%;
    background-color: #f7f7f7 !important;
    margin: -300px auto;
    margin-top: -160px;
  }
  .ul li{

  }
  .nav-ul li{
    float: left;
    margin-right: 40px;
  }
  .login li{
    float: right;
    margin-right: 40px;
  }
  .search-input{
    margin-top: 25px;
    width: 600px;
    height: 38px;
    right: -200px;
  }
  .search-button{
    position: absolute;
    margin-top: 25px;
    margin-left: 210px;
  }
  .zhongjian-active{
    height:220px;
    width:1100px;
    position: relative;
    background-color: #f7f7f7;
    margin: auto;
  }
  .hot-content-active{
    background-color: #f7f7f7;
    width: 700px;
    height: 100px;
    position: relative;
    right: -400px;
    top: -150px;
  }
  .hot-content-active span{
    margin-right: 20px;
  }
  .hot-content-active dl{
    width: 800px;
    float: left;
  }
  .hot-content-active dd {
    margin-right: -30px;
    margin-top: 5px;
    line-height: 10px;
  }
  .index-h1{
    text-align: center;
    position: relative;
    top: 200px;
    font-size: 24px;
  }
  .index-product{
    width: 1200px;
    margin: 550px auto;
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


  #flash{margin: 50px auto;position: relative;height: 10px;
    margin: 8px auto 8px;
    width: 1100px;}
  #flash #plays{list-style: none;position:relative;}
  #flash #plays li{display: none;position:relative;top: -160px;right: 40px}
  #button{position: absolute;bottom:-140px;left:470px;list-style: none;}
  #button li{margin-left: 10px;float: left;}
  #button li div{width:12px;height: 12px;background:#DDDDDD;border-radius: 6px;cursor: pointer;}


</style>
