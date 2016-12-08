require('babel-core/register');
var gulp = require("gulp");
var gutil = require("gulp-util");
// var notify = require("gulp-notify");
var webpack = require("webpack");
var WebpackDevServer = require("webpack-dev-server");
var webpackConfig = require("./webpack.config.js");
// var mocha = require("gulp-mocha");

// The development server (the recommended option for development)
gulp.task("default", ["build-dev", "webpack:server"]);

// Build and watch cycle (another option for development)
// Advantage: No server required, can run app from filesystem
// Disadvantage: Requests are not blocked until bundle is available,
//               can serve an old app on refresh
gulp.task("build-dev", ["webpack:build-dev"], function() {
    gulp.watch(["src/app/**/*", "src/sass/**/*"], ["webpack:build-dev"]);
    // gulp.watch(["src/app/**/*", "src/app/test/**/*.js"], ["mocha"]);
});

// gulp.task("mocha", function(){
//     return gulp.src(['src/app/test/**/*.js'], { read: false })
//         .pipe(mocha())
//         .on('error', gutil.log)
//         .on('error', notify.onError("Error: <%= error.message %>"));
// })

// Production build
gulp.task("build", ["webpack:build"]);

gulp.task("webpack:build", function(callback) {
    webpack(webpackConfig(false), function(err, stats) {
        if(err) throw new gutil.PluginError("webpack:build", err);
        gutil.log("[webpack:build]", stats.toString({
            chunks: false,
            colors: true
        }));
        callback();
    });
});


gulp.task("webpack:build-dev", function(callback) {
    var devCompiler = webpack(webpackConfig(true));
    devCompiler.run(function(err, stats) {
        if(err) throw new gutil.PluginError("webpack:build-dev", err);
        gutil.log("[webpack:build-dev]", stats.toString({
            chunks: false,
            colors: true
        }));
        callback();
    });
});

gulp.task("webpack:server", function(callback) {
    new WebpackDevServer(webpack(webpackConfig(true)))
        .listen(8080, "localhost", function(err) {
            if(err) throw new gutil.PluginError("webpack-dev-server", err);
            gutil.log("[webpack-dev-server]", "http://localhost:8080/");
        });
});
