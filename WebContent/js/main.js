var d = new Date();
var weekday = new Array(7);
weekday[0] =  "Sunday";
weekday[1] = "Monday";
weekday[2] = "Tuesday";
weekday[3] = "Wednesday";
weekday[4] = "Thursday";
weekday[5] = "Friday";
weekday[6] = "Saturday";

var n = weekday[d.getDay()];

var c = "c";/*occupy*/
var f = "f";/*free*/
var na = "na";/*available*/

var data = [
    [c,f,c,c,na,f], //sunday
    [c,f,f,c,f,na],
    [c,c,c,f,c,f],
    [c,f,c,c,c,f],
    [c,na,na,f,c,na],
    [c,f,f,c,f,f],
    [c,na,na,na,f,f]]; //saturday

var table = document.createElement('table');

/* Just header */
var tr = document.createElement('tr');   
for(var i  = 0 ; i < 7 ; i++){
    var td = document.createElement('td');
    var text = document.createTextNode(weekday[i]);
   
    td.onclick = (function(par1) {
        return function() {
           updateColumn(par1);
        };
    })(i);
   
    td.appendChild(text);
    tr.appendChild(td);
}
table.appendChild(tr);

/*Calendar*/
for (var i = 0; i < 12; i++){ /* For every row (hour)*/
    var tr = document.createElement('tr');   
    for(var k  = 0 ; k < 7 ; k++){ /* For every column (day)*/
        var td = document.createElement('td');
        //td.className = (data[k][i]== c) ? "taken" : "free";
        
        switch(data[k][i]){
        case c:
        	td.className ="taken"
        	break;
        case f:
        	td.className ="free"
        	break;
        default:
        	td.className ="notav"
        }
        
        
        td.id = "hour-" + i + "-" + k;
            
        var text = document.createTextNode((i + 10) + ":00 - " + (i + 10) + ":30");
        
        td.onclick = (function(par1, par2) {
            return function() { /*just to show all the modal forms*/

               document.getElementById('id05').style.display='block';/*showup modal form "new appointment" (parent side)*/
               updateCell(par1, par2);
               document.getElementById('id09').style.display='block';
               updateCell(par1, par2);
            };
        })(i, k);
        
        td.appendChild(text);        
        tr.appendChild(td);
    }

    table.appendChild(tr);
}


function updateColumn(i){
    for(var k = 0; k < 12; k++){
      data[k][i] = c;
      document.getElementById('hour-' + k + '-' + i).classList.remove("free");
      document.getElementById('hour-' + k + '-' + i).className = "taken";
    }
}

function updateCell(i, k){
    data[k][i] = c;
    document.getElementById('hour-' + i + '-' + k).classList.remove("free");
    document.getElementById('hour-' + i + '-' + k).className = "taken";
}


function updateCell(i, k){
    data[k][i] = f;
    document.getElementById('hour-' + i + '-' + k).classList.remove("taken");
    document.getElementById('hour-' + i + '-' + k).className = "free";
}

document.getElementById('calendar').appendChild(table); 