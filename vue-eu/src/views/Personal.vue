<template>
  <div>
    <div class="user_default" style="margin-top: 50px">
      <Head></Head>
      <div class="user_default_in">
        <!-- 用户左侧栏目 -->
        <div class="user_left">
          <div class="user_info_block">
            <dl>
              <dt><img :src="member.photo"></dt>
              <dd class="nickname">{{member.nickName||'加载中...'}}</dd>
              <dd class="edit_user_info"><router-link to="/user/myinfo">编辑信息</router-link></dd>
            </dl>
          </div>

          <div class="user_nav">
            <div class="block" v-for="(v,k) in nav" :key="k">
              <div class="title">
                <img :src="'static/Home/usericon/'+v.icon" alt="">
                <!-- <i :type="v.icon" :class="'fa '+v.icon" /> -->
                <span>{{v.name}}</span>
              </div>
              <div @click="splitIndex(vo.id)" :class="currentIndex == vo.id ? 'nav_item_active' : 'nav_item'" v-for="(vo,key) in v.children" :key="key">
                <router-link :to="vo.url">{{vo.name}}</router-link>
              </div>
            </div>
          </div>
        </div>
        <div class="user_right">
          <div class="right-top">
            <img width="1200px" height="375px" src="/static/img/10.jpg">
          </div>
            <router-view></router-view>
        </div>
      </div>
    </div>
    <foot></foot>
  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: "personal",
    data(){
      return{
        member: [], //用户信息
        currentIndex: 1,
        nav: [
          {
            name: '个人中心',
            icon: 'user.png',
            children: [
              {id: 1, name: '首页', url: '/index'},
              {id: 2, name: '用户资料', url: '/user/myinfo'},
              {id: 3, name: '收藏/关注', url: '/user/love'},
              {id: 4, name: '我的足迹', url: '/user/myhistory'},
              {id: 5, name: '我的预约', url: '/user/order'}
            ],
          },
        ],
        user_info:{},
        isLogin:false,
      }
    },
    watch:{
      '$route.path':function(newVal,oldVal){
        this.currentIndex = this.$route.query.currentIndex

      }
    },
    mounted() {
      axios.get("/eu/findUserByMember",{params:
          {mid: localStorage.getItem("mid")}
      }).then(res=>{
        this.member = res.data.data
      })

      this.currentIndex = this.$route.query.currentIndex
      console.log(this.currentIndex)
    },
    methods: {
        logout(){
          localStorage.removeItem("mid");
          this.$router.push("/login")
        },
        splitIndex(id){
          this.currentIndex = id;
          document.getElementsByClassName("nav_item_active")
        }
      }
    }
</script>

<style lang="scss" scoped>
  .product-img{
    margin-top: 10px;
    margin-left: 10px;
    width: 230px;
    height: 230px;
  }
  .product-gname{
    width: 252.8px;
    height: 25px;
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
  .nav_item_active a{
    color: red;
  }
  .goods{
    border-radius: 8px;
    box-shadow: 0px 0px 10px 2px #e0e0e0;
    width: 250px;
    height: 290px;
    background-color: #ffffff;
    list-style: none;
    display: inline-block;
    margin-top:5px;
    margin-left: 10px
  }

  .user_default{
    background: #f1f1f1;
    padding-bottom: 30px;
    width: 100%;
    height: 1100px;
  }
  .user_left{
    float: left;
    width: 234px;
    height: 1082px;
    margin-right: 20px;
    .user_nav{
      margin-top: 20px;
      background: #fff;
      padding: 20px;
      height: 710px;
      .block{
        border-bottom: 1px solid #efefef;
        padding-bottom: 15px;
        margin-bottom: 15px;
        height: 260px;
        margin-top: 50px;
        .nav_item{
          line-height: 60px;
          margin-left: 55px;
          cursor: pointer;
          :hover{
            color:#ca151e;
          }
        }
        .nav_item_active{
          line-height: 60px;
          margin-left: 55px;
          cursor: pointer;
          color:#ca151e;
        }
        &:last-child{
          border-bottom: none;
        }
      }
      .title{
        font-size: 16px;
        margin-bottom: 10px;
        font-weight: bold;
        img{
          margin-left: 26px;
          margin-right: 10px;
        }
      i{
           color:#ca151e;
          margin:0 6px 0 24px;
          font-size: 14px;
       }
      }
    }
    .user_info_block{
      background: #fff;
      padding: 20px;
      height: 300px;
      .nickname{
        width: 110px;
        overflow: hidden;
        height: 19px;
      }
      .user_stepbar{
        margin-top: 20px;
      }
      .user_safe{
        margin-top: 6px;
        i{
          margin-right: 6px;
        }
        .safe_icon .success{
          color:#67c23a;
        }
      }
      .progress{
        width: 124px;
        display: inline-flex;
      }
      dl{
        &:after{
          clear:both;
          display: block;
          content:'';
          height: 60px;
        }
        dt{
          float: left;
          width: 160px;
          height: 160px;
          border: 1px solid #f4f4f4;
          border-radius: 50%;
          img{
            width: 100px;
            height: 100px;
            border-radius: 50%;
            position: relative;
            top: 25px;
            right: -40px;
          }
        }
        dd{
          font-size: 14px;
          float: left;
          margin-top: 10px;
          text-align: center;
          &.edit_user_info{
            border: 1px solid #ca151e;
            line-height: 20px;
            padding: 0 15px;
            margin-top: 30px;
            margin-right: auto;
            a{
              color: #ca151e;
            }
          }
        }
      }
    }
  }
  .user_right{
    margin-top: 10px;
    float: left;
    width: 1200px;
    padding-top: 5px;
    background: #fff;
  }

</style>
<style>
  .user_stepbar .el-progress__text{
    min-width: 20px;
  }
</style>
