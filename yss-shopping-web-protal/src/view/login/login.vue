<style lang="less">
  @import './login.less';
</style>

<template>
  <div class="login">
    <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <login-form @on-success-valid="handleSubmit"></login-form>
          <p class="login-tip">输入任意用户名和密码即可</p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import LoginForm from '_c/login-form'
import { mapActions } from 'vuex'
import { setToken } from '@/libs/util'
import { denglu } from '@/api/user'

export default {
  components: {
    LoginForm
  },
  methods: {
    ...mapActions([
      'handleLogin',
      'getUserInfo'
    ]),
    handleSubmit ({ userName, password }) {
      denglu(userName, password).then(res => {
        if (res.data.code == 1) {
          // 将用户ID存储在本地
          setToken(res.data.data.uid)

          // 跳转
          this.$router.push({
            name: this.$config.homeName
          })
        } else if (res.data.code == 0) {
          alert(JSON.stringify(res.data.msg))
        }
      })
    }
  }
}
</script>

<style>

</style>
