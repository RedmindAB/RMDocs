/**
  This is a small node program that connects to RMReport
  and then produces a filtered test XLS file.
*/

var http = require('http');
var fs = require("fs");
var spawn = require('child_process').spawn;
var filterFile = "";

//Argument handling
var Arg = {
  rmreport: "127.0.0.1",
  rmreportPort: 4567,
  projectPath: "/",
  rmdocsJar: "rmdocs.jar",
  java: "/usr/bin/java",
  suite: null
};

function splitArguments(){
  var cwd = process.cwd();
  console.log("current working directory: "+cwd);
  filterFile = cwd+"/filter.txt";
  var args = process.argv.slice(2);
  for (var i = 0; i < args.length; i++) {
    handleArgument(args[i]);
  }
  console.log("##### Running Configuration #####");
  console.log(Arg);
}

function handleArgument(arg){
  var arguments = arg.split("=");
  if(arguments.length != 2){
    var error = "Argument '"+ arg + "' is mallformed, arguments must be formatted as 'key=value'";
    console.error(error);
  }
  else{
    Arg[arguments[0]] = arguments[1];
  }
}
//RMReport

function get(rmpath, callback, isNotJson){
  return http.get({
  hostname: Arg['rmreport'],
  port: Arg['rmreportPort'],
  path: rmpath,
  agent: false
}, function (res) {
  var body = ""
    res.on('data', function(data) {
      body += data;
    });
    res.on('end', function() {
      var results;
      if(!isNotJson){
        results = JSON.parse(body);
      }
      else {
        results = body;
      }
      callback(results);
    });
});
}

function createXLS(){
  console.log("Getting suite by name");
  get("/api/suite/getsuites", function(suites){
    for (var i = 0; i< suites.length; i++) {
      var name = suites[i].name;
      if(name == Arg["suite"]){
        Arg["suiteid"] = suites[i].id;
        break;
      }
    }
    var suiteid = Arg["suiteid"];
    console.log("Suite id for "+Arg["suite"]+" = "+suiteid);
    var data = getTestFilterData(suiteid);
  });
}

function getTestFilterData(suiteid){
  console.log("Getting filterdata for "+Arg["suite"]+"using id: "+suiteid);
  var url = "/api/stats/rmdocs/"+suiteid;
  console.log(url);
  get(url, function(filter){
    fs.writeFile(filterFile, filter, "UTF-8", function(){
      console.log("Executing RMDocs");
      var p = spawn(Arg["java"],
      ["-jar", Arg["rmdocsJar"], "-o", ".xls", "-p", Arg["projectPath"], "-filter", filterFile]);

      p.stdout.on('data', function(data){
        console.log('stdout: ' + data);
      });
      p.stderr.on('data', function(data){
        console.log('stderr: ' + data);
      });

      p.on('close', function(){
        console.log("DONE!");
      });
    });
  }, true);
}

splitArguments();
createXLS();
