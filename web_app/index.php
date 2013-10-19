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
				<form class="form-horizontal" role="form" method="post" action="index.php" >
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

			<?php
				// Connection to the server
				$conn = mysqli_connect('localhost', 'root', '', 'android_app') or die("bla");
				// if some error 
		        if (mysqli_connect_errno()){
		          	echo "Failed to connect to MySQL: " . mysqli_connect_error();
		        }
				// http://diskuse.jakpsatweb.cz/?action=vthread&forum=9&topic=145971 We need isset(), but also
				// http://stackoverflow.com/questions/19465195/mysql-stores-only-1-as-a-value-no-matter-what-numbers-i-submit-in-the-form
		        $longti = isset($_POST['inputLongitude']) ? $_POST['inputLongitude'] : null; 
		        $lati = isset($_POST['inputLatitude']) ? $_POST['inputLatitude'] : null;
				$sql = "INSERT INTO `table`(`key_prim`, `lati`, `longti`) VALUES (`key_prim`,$lati,$longti)";
				mysqli_query($conn,$sql);
			?>

			<div class="col-xs-6 col-sm-6 col-md-8">	
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Primary Key in DB</th>
							<th>Longitude</th>
							<th>Latitude</th>
							<th>Json</th>
						</tr>
					</thead>
					<tbody>
						<?php
							$cn = mysqli_connect('localhost', 'root', '', 'android_app') or die("bla");
							// It prints the results from DB through a SQL request
							$q = "SELECT `key_prim`,`lati`,`longti` FROM `table`" ;
							$results = mysqli_query($cn, $q) or die(mysqli_error($cn));
							$json = array();
							// It fetches results from db and stores them in a row
							// http://stackoverflow.com/questions/2974011/while-row-mysql-fetch-arrayresult-how-many-loops-are-being-performed
							while ($row = mysqli_fetch_array($results)){
								$json["key_prim"] = $row["key_prim"];
						        $json["lati"] = $row["lati"];
						        $json["longti"] = $row["longti"];
								$text = json_encode($json, JSON_PRETTY_PRINT);

								echo "<tr>
								<td>".$row['key_prim']."</td>
								<td>".$row['longti']."</td>
								<td>".$row['lati']."</td>
								<td><pre>".$text."</pre></td>
								</tr>";
							}
						?>
					</tbody>
				</table>
			</div>


		</div>	
	</body>
</html>