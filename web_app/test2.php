<?php

$host = "localhost";
$username = "root";
$password = "";
$database = "android_app";
$dsn = "mysql:host=$host;dbname=$database";

TRY {
$conn = new PDO( $dsn, $username, $password );
$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

if (isset($_POST['submit'])) {
    $firstname = $_POST['firstname'];
    $lastname = $_POST['lastname'];
    $gender = $_POST['gender'];
    $Console = $_POST['Console'];

    if (isset($_POST['id'])) {
        //update mode, we have both POST data and ID, update the record
        $id = $_POST['id'];

        $sql = "UPDATE userprofile SET"
            . "firstname=".$conn->quote($firstname)
            . ",lastname=".$conn->quote($lastname)
            . ",sex=".$conn->quote($gender)
            . ",console=".$conn->quote($Console)
            . " WHERE id = ".$conn->quote($id);
        $userdata = $conn->query($sql);
    } else {
        // insert mode, there is no ID, but there are data, insert them as new
        $sql = "INSERT INTO userprofile("
            . "firstname, lastname, sex, console"
            . " ) VALUES ("
            . $conn->quote($firstname).","
            . $conn->quote($firstname).","
            . $conn->quote($firstname).","
            . $conn->quote($firstname).")";
        $userdata = $conn->query($sql);
    }
} elseif (isset($_GET['id'])) {
    // edit mode, no POST data, but there is an ID param, prepopulate the form
    $userEditDataRows = $conn->query('SELECT * FROM userdata WHERE id ='.$conn->quote($_GET['id']));
    if (sizeof($userEditDataRows)>0) {
        $row = $userEditDataRows[0];
        $firstname = $row['firstname'];
        $lastname = $row['lastname'];
        $gender = $row['sex'];
        $Console = $row['console'];
        $id = $_GET['id'];
    }

} else {
    // set empty data
    $firstname = '';
    $lastname = '';
    $gender = '';
    $Console = '';
    $id = false;
}
    //build the table
    $sql = "SELECT * FROM and_table";
    $userdata = $conn->query($sql);
    $table = '<table>';
    $table .= '<tr>';
    $table .= '<th>First Name</th>
          <th>Last Name</th>
          <th>Gender</th>
          <th>Console</th>
          <th>Edit</th>';
    $table .= '</tr>';
    foreach ($userdata as $userdata) {
        $table .= '<tr>';
        $table .= '  <td>' . $userdata['firstname'] . '</td>';
        $table .= '  <td>' . $userdata['lastname'] . '</td>';
        $table .= '  <td>' . $userdata['gender'] . '</td>';
        $table .= '  <td>' . $userdata['Console'] . '</td>';
        $table .= '  <td><a href="/process.php?id='.$userdata['id'].'">Edit</a></td>';
        $table .= '  </tr> ';
    }

    $table .= '</table>';

} catch (PDOException $e) {
    exit("Connection failed: " . $e->getMessage());
}
?>

<!Doctype html public>
<html>        
<body>

Please fill out the following form:

<table border="1" cellpadding="10">

    <td>
        <h1> Games Console Survey </h1>
        <form action="test.php" method = "post">

            First Name: <br /> <input type="text" name="firstname" value="<?php $firstname ?>" /><br />
            <br />

            Surname: <br /> <input type="text" name="lastname" value="<?php $lastname ?>" /> <br />

            <br />

            <u>Gender</u>: <br />
            <br />


            <input type="radio" name="gender" value="Male" <?php if($gender == 'Male') echo "checked=checked"; ?> /> Male<br />
            <input type="radio" name="gender" value="Female" <?php if($gender == 'Feale') echo "checked=checked"; ?>/> Female <br />

            <br />

            <u>I Have The Following:</u> <br />
            <br />

            <input type="checkbox" name="Console" value="Playstation 3" <?php if($Console == 'Playstation 3') echo "checked=checked"; ?> /> Playstation 3<br />
            <input type="checkbox" name="Console" value="Xbox 360" <?php if($Console == 'Xbox 360') echo "checked=checked"; ?> />  Xbox 360 <br />
            <input type="checkbox" name="Console" value="Wii" <?php if($Console == 'Wii') echo "checked=checked"; ?> />  Wii <br />

            <?php if($id !== false):?>
                <input type="hidden" name="id" value="<?php echo $id; ?>"/>            
            <?php endif; ?>            

            <br />
            <input type="submit" name="submit"/>
        </form>

    </td>
</table>

<h1>Current data:</h1>
<?PHP echo $table ?>
</body>

</html>