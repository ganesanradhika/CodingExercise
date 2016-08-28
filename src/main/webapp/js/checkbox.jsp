<html>
<head>
<title>jQuery Hello World</title>

<script type="text/javascript" src="./jquery-3.1.0"></script>

</head>

<body>

<script type="text/javascript">

$(document).ready(function(){
 $("#msgid").html("This is Hello World by JQuery");
 
});

$("#isCheck").click(function () {

alert($('input:checkbox[name=checkme]').is(':checked'));

});

</script>

This is Hello World by HTML

<div id="msgid">
</div>

<input type="checkbox" name="checkme"/>
<input type="button" value="Is Check" id="isCheck"/>
</body>
</html>
