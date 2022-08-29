module.exports = {
  proxy:{
    '/eu' : {
      target: 'http://localhost:8088',
      changeOrigin: true,
      pathRewrite:{
        '^/eu': ''
      }

    }
  }
}
