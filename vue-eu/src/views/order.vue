<template>
  <div class="right-down">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-table
        :data="orderList"
        style="width: 100%">
        <el-table-column
          prop="oid"
          label="编号"
          width="120">
        </el-table-column>
        <el-table-column
          prop="aname"
          label="场地名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="gname"
          label="方案名称"
          width="280">
        </el-table-column>
        <el-table-column
          prop="time"
          label="日期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="starttime"
          label="开始时间"
          width="150">
        </el-table-column>
        <el-table-column
          prop="endtime"
          label="结束时间"
          width="150">
        </el-table-column>
        <el-table-column
          prop="status"
          label="使用状态"
          width="150"
        >
          <template slot-scope="scope">
              {{scope.row.status | filterStatus}}
          </template>
        </el-table-column>
      </el-table>
    </el-tabs>
    <div style="margin-top: 60px;position: relative;top: -20px">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="ordertotal"
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
      name: "order",
      data(){
        return{
          orderList: [],
          activeName: '',
          currentLove: -1,
          pageSize: 2,  //页面最大数据条数
          currentPage: 1,
          ordertotal: [],
        }
      },
      filters:{
        filterStatus(val){
          if(val == 0){
            return "已使用"
          }else{
            return "未使用"
          }
        }
      },
      mounted() {
        axios.get("/eu/findUserByMember",{params:
            {mid: localStorage.getItem("mid")}
        }).then(res=>{
          this.member = res.data.data
        })
        this.selectAll();
      },
      methods:{
        logout(){
          localStorage.removeItem("mid");
          this.$router.push("/login")
        },
        CurrentChange(val){
            this.currentPage = val;
            this.selectAll();
        },
        selectAll(){
          let page = (this.currentPage - 1) * (this.pageSize);
          axios.get("/eu/selectAll", {params:{page: page, rows: this.pageSize, mid: localStorage.getItem("mid")}
          }).then(res=>{
            this.orderList = res.data.orderInfoList;
            this.ordertotal = res.data.ordertotal;
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
</style>
