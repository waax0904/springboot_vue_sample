const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: { port: 5500 },

  // chainWebpack: (config) => {
  //   config.plugin('define').tap((definitions) => {
  //     Object.assign(definitions[0], {
  //       '__VUE_PROD_HYDRATION_MISMATCH_DETAILS__': JSON.stringify(false),
  //     });
  //     return definitions;
  //   });
  // }

})