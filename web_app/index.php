<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Android WebApp</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<meta name="description" content="Check this project on GitHub">
		<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/styles.css">
		<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script> 
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
		<link rel="shortcut icon" href="images/icon.png"> 
	</head> 

	<body>
		
		<div class="row">
			<!-- Displays only our logo, nothing else.  --> 
			<div class="page-header">
				<img src="images/hochschule.jpeg" class="imgcenter" */>
				<!-- <h1>This is Web-App which stores GEO POI in a MySQL server</h1> -->
				<!-- <p>For more about this site check our <a href="https://github.com/Johnmalc/AndroidApp">GitHub here</a></p> -->
			</div>
			
			<div class="col-xs-9 col-md-6">
			<!-- Creates a form which is sent to sent.php -->
				<form id="resetThen" class="form-horizontal" role="form" method="post" action="sent.php" >
				  	<div class="form-group">
					    <label for="inputLatitude" class="col-lg-2 control-label">Latitude</label>
					    <div class="col-xs-6">
					      <input type="number" class="form-control" id="inputLatitude" placeholder="Width (Fi)" name="inputLatitude">
						</div>
				 	</div>
				 	<div class="form-group">
					    <label for="inputLongitude" class="col-lg-2 control-label">Longitude</label>
					    <div class="col-xs-6">
					      <input type="number" class="form-control" id="inputLongitude" placeholder="Length (Lambda)" name="inputLongitude">
					    </div>
				  	</div>
				  	
					<div class="form-group">
					    <label for="inputTitel" class="col-lg-2 control-label">Titel</label>
					    <div class="col-xs-6">
					      <input type="text" class="form-control" id="inputTitel" placeholder="Write a titel for a location" name="inputTitel"></input>
						</div>
				 	</div>
				 	<div class="form-group">
					    <label for="inputSnippets" class="col-lg-2 control-label">Snippet</label>
					    <div class="col-xs-6"> 
					      <input type="text" class="form-control" id="inputSnippets" placeholder="Write a small text describing a location" name="inputSnippets"></input>
						</div>
				 	</div>				
					<div class="form-group">
					    <div class="col-lg-offset-2 col-xs-6">
					    	<button type="submit" name="submit" class="btn btn-primary">
					    		<span class="glyphicon glyphicon-floppy-save"></span> Save
					    	</button>
					    	&nbsp;&nbsp;
					    	<button type="button" id="configreset" class="btn btn-info" name="reset_form" value="Reset Form">
								<span class="glyphicon glyphicon-refresh"></span>  Clear this form
							</button>
					    </div>
					</div>					
				</form>
				
				<!-- Taken from http://stackoverflow.com/questions/16452699/how-to-reset-a-form-using-jquery-with-reset-method -->
				<!-- Resets the form-->
				<script type="text/javascript">
					$('#configreset').click(function(){
			            $('#resetThen')[0].reset();
			 		});
				</script>
			</div>
			
			<div class="col-xs-9 col-md-6">	
				<!-- http://designshack.net/articles/css/beating-borders-the-bane-of-responsive-layout/ -->
				<div class="column">
					<h3>Who developed this site?</h3>
					<p>This website was created by <a href="https://github.com/Johnmalc/AndroidApp">John Malc</a></p>
					<h4>What it does?</h4>
					<p>It stores values in MySQL and our Android application (check out GitHub) uses it to show the Augmented Reality in Google Maps</p> 
					<h4>What technology do we use?</h4>
					<p>JQuery, Bootstrap and PHP & MySQL</p>
					<h4>Is this responsive?</h4>
					<p>It is !</p>
					<h5>You want to see values right now?</h5>
					<p><a href="http://projekty.komentovaneudalosti.cz/android/view.php">Look here</a></p>
				</div>
			</div> 

		</div> <!-- Ends the whole Bootstrap row -->

		<!-- Google Analytics -->
		<script>
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		  ga('create', 'UA-11552074-6', 'komentovaneudalosti.cz');
		  ga('send', 'pageview');
		</script>	
	
	</body>
</html>