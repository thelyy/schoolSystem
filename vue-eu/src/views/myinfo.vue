<template>
  <div class="right-down" style="height: 717px">
    <div class="box-wrapper">
      <div style="margin: 80px auto;width: 60%">
        <el-form ref="form" :model="member" label-width="80px">
          <el-form-item label="头像">
            <a @click="$refs.inputImg.click()">
              <el-avatar
                class="image"
                :src="member.photo"
                fit="large"
              >
              </el-avatar>
            </a>

            <input ref="inputImg" type="file" name="filePath" style="display: none" @change="updateHeadImg()"/>
          </el-form-item>
          <el-form-item label="用户名">
            <el-input v-model="member.nickName"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="member.tel"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="member.email" disabled></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="margin: 10px 0 0 0px;width: 90px" type="primary" @click="updateMemberInfo">修改</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
    import axios from "axios";

    export default {
      name: "myinfo",
      data(){
        return{
          member: [], //用户信息
        }
      },
      mounted() {
        axios.get("/eu/findUserByMember",{params:
            {mid: localStorage.getItem("mid")}
        }).then(res=>{
          this.member = res.data.data
          console.log(this.member)
        })
      },
      methods:{
        logout(){
          localStorage.removeItem("mid");
          this.$router.push("/login")
        },
        updateMemberInfo(){
          var formData = new FormData();
          formData.append("mid", localStorage.getItem("mid"));
          formData.append("tel", this.member.tel);
          formData.append("nickName", this.member.nickName);
          axios.post("/eu/updateMemberInfo",formData).then(res=>{
            this.options = res.data

          })
        },
        getFile(){
          // var file = window.URL.createObjectURL(this.$refs.inputImg.files[0])
          // this.member.photo = file
        },
        updateHeadImg(){
          var file = window.URL.createObjectURL(this.$refs.inputImg.files[0])
          this.member.photo = file
          var formData = new FormData();
          formData.append("file", this.$refs.inputImg.files[0]);
          formData.append("mid", localStorage.getItem("mid"));
          let headers = {headers: {"Content-Type": "multipart/form-data"}}
          axios.post("/eu/updateHeadImg",formData, headers).then(res=>{
            this.options = res.data
          })
        }
      }
    }
</script>

<style scoped>
  .box-wrapper {
    float: left;
    overflow: hidden;
    width: 46.8%;
    margin: 0 3%;
    padding-bottom: 72px;
    border-radius: 12px;
  }
  .image{
    border-radius: 50%;
    width: 160px;
    height: 160px;
  }
</style>
