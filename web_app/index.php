<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Android WebApp</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="utf-8">
		<meta name="description" content="*******************">
		<link rel="stylesheet" href="http://bootswatch.com/readable/bootstrap.min.css"> <!-- http://bootswatch.com/ -->
		<link rel="stylesheet" href="css/bootstrap.css"> 
		<link rel="stylesheet" href="css/jquery.pnotify.default.css"> 
		<link rel="stylesheet" href="css/styles.css">
		<script src="http://code.jquery.com/jquery-1.10.1.min.js" ></script> 
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.pnotify.min.js"></script>
		<!-- <link rel="shortcut icon" href="img/favicon.ico"> -->
	</head> 

	<body>

		<div class="row">
			<div class="page-header">
				<h1>This is Web-App which stores GEO POI in a MySQL server <small>Developed by <a href="http://www.twitter.com/malcjohn">@malcjohn</a></small></h1>
				<h2>For more about this site check our GitHub <a href="https://github.com/Johnmalc/AndroidApp">here</a></h2>
			</div>


			<div class="col-xs-6 col-sm-6 col-md-4">
				<form class="form-horizontal" role="form" method="post" action="sent.php" >
				  	<div class="form-group">
					    <label for="inputLongitude" class="col-lg-2 control-label">Longitude</label>
					    <div class="col-lg-10">
					      <input type="number" class="form-control" id="inputLongitude" placeholder="longitude" name="inputLongitude">
					    </div>
				  	</div>
				  	<div class="form-group">
					    <label for="inputLatitude" class="col-lg-2 control-label">Latitude</label>
					    <div class="col-lg-10">
					      <input type="number" class="form-control" id="inputLatitude" placeholder="latitude" name="inputLatitude">
						</div>
				 	</div>
					<div class="form-group">
					    <div class="col-lg-offset-2 col-lg-10">
					    <!-- http://pinesframework.org/pnotify/ -->
					    	<button type="submit" onclick="$.pnotify({ title: 'Sticky Info',text: 'Your number have been saved',type: 'info', hide: false});" 
					    	name="submit" class="btn btn-primary">Save</button>
					    </div>
					</div>
				</form>
			</div>

			<div class="col-xs-6 col-sm-6 col-md-8">	
			
			</div>
		</div>	
	</body>
</html>
