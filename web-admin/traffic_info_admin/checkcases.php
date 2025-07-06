<?php session_start(); 


include_once 'database.php';
if (!isset($_SESSION['user']) ) {
  # code...
  header('Location:./logout.php');
}
?>
<?php

 $sid =$fname =$lname = $classroom = $dob = $gender = $address = $parent=" ";
              

if(isset($_GET['update'])){
  $update = "SELECT * FROM student WHERE sid='".$_GET['update']."'";
  $result = $conn->query($update);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $sid = $row['sid'];
                $fname = $row['fname'];
                $lname = $row['lname'];
                $classroom = $row['classroom'];
$email = $row['email'];
                $dob = date_format(new DateTime($row['bday']),'m/d/Y');
                //echo $dob;
                $gender = $row['gender'];
                  $address = $row['address'];
                $parent=$row['parent'];
                
    }
}
}

?>


<!DOCTYPE html>


<html>
<head>
  
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Cases</title><link rel="icon" href="../img/favicon2.png">
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
  

  <link rel="stylesheet" href="bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <link rel="stylesheet" href="bower_components/select2/dist/css/select2.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
    // Setup - add a text input to each footer cell
      scrollX: true,
    $('#example thead tr')
        .clone(true)
        .addClass('filters')
        .appendTo('#example thead');

 
    var table = $('#example').DataTable({
        orderCellsTop: true,
        fixedHeader: true,
        initComplete: function () {
            var api = this.api();
 
            // For each column
            api
                .columns()
                .eq(0)
                .each(function (colIdx) {
                    // Set the header cell to contain the input element
                    var cell = $('.filters th').eq(
                        $(api.column(colIdx).header()).index()
                    );
                    var title = $(cell).text();
                    $(cell).html('<input type="text" placeholder="' + title + '" />');
 
                    // On every keypress in this input
                    $(
                        'input',
                        $('.filters th').eq($(api.column(colIdx).header()).index())
                    )
                        .off('keyup change')
                        .on('change', function (e) {
                            // Get the search value
                            $(this).attr('title', $(this).val());
                            var regexr = '({search})'; //$(this).parents('th').find('select').val();
 
                            var cursorPosition = this.selectionStart;
                            // Search the column for that value
                            api
                                .column(colIdx)
                                .search(
                                    this.value != ''
                                        ? regexr.replace('{search}', '(((' + this.value + ')))')
                                        : '',
                                    this.value != '',
                                    this.value == ''
                                )
                                .draw();
                        })
                        .on('keyup', function (e) {
                            e.stopPropagation();
 
                            $(this).trigger('change');
                            $(this)
                                .focus()[0]
                                .setSelectionRange(cursorPosition, cursorPosition);
                        });
                });
        },
    });
});
  </script>


    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/fixedheader/3.2.3/js/dataTables.fixedHeader.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css
">
<link rel="stylesheet" type="text/css" href="
https://cdn.datatables.net/fixedheader/3.2.3/css/fixedHeader.dataTables.min.css
">
</head>

<body class="hold-transition skin-green sidebar-mini">
<div class="wrapper">

  <!-- Main Header -->
 <?php include_once 'header.php'; ?>
  <!-- Left side column. contains the logo and sidebar -->
  <?php include_once 'sidebar.php'; ?>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Cases
        <small>Case Details</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Case</a></li>
        <li class="active">Details</li>
      </ol>
    </section>

    <!-- Main content -->


    <section class="content">

 <div class="row">

  <?php if (!isset($_GET['update'])) { ?>


   
          

              <?php

              if (isset($_POST['submit'])) {
                $sid = $_POST['sid'];
                $fname = $_POST['fname'];
                $lname = $_POST['lname'];
                 $email = $_POST['email'];
                $classroom = $_POST['classroom'];
 $password = $_POST['password'];
          
                //echo $dob;
                $gender = $_POST['gender'];
      
                $parent=" ";
                if(isset($_POST['parent'])){
                $parent = $_POST['parent'];}





                  try {


                   

                    $sql = "INSERT INTO student (sid,fname,lname,gender,semester_name,department_name,email) VALUES ('".$sid."', '".$fname."', '".$lname."','".$gender."','".$parent."','".$classroom."','".$email."')";

                  if ($conn->query($sql) === TRUE) {
                            $sql1 = "INSERT INTO user (role,email,password) VALUES ('student', '".$email."','".$password."')";
                            if($conn->query($sql1)==TRUE){
                                 echo "<script type='text/javascript'> var x = document.getElementById('truemsg');
x.style.display='block';</script>";
                            }

                        
                      } else {
                            }
                    
                  } catch (Exception $e) {
                    
                  }





                  
                # code...
                                            }

              ?>



         <?php }elseif (isset($_GET['update'])) { ?>



<!--Update************************************************************************************************************* -->            
     <div class="col-xs-4">

   

         <div class="alert alert-success alert-dismissible" style="display: none;" id="truemsg">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-check"></i> Success!</h4>
                Update Student Successfully 
              </div>
<!-- general form elements -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Update Student</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form" method="POST" >
              <div class="box-body">

                 <div class="form-group">
                  <label for="exampleInputPassword1">Student ID</label>
                  <input name="sid" type="text" class="form-control" id="exampleInputPassword1"  required value=<?php echo "'".$sid."'"; ?>>
                </div>

                <div class="form-group">
                  <label for="exampleInputPassword1">First Name</label>
                  <input name="fname" type="text" class="form-control" id="exampleInputPassword1" required value=<?php echo "'".$fname."'"; ?>>
                </div>

                <div class="form-group">
                  <label for="exampleInputPassword1">Last Name</label>
                  <input name="lname" type="text" class="form-control" id="exampleInputPassword1"  required value=<?php echo "'".$lname."'"; ?>>
                </div>

             
                <div class="form-group">
                  <label for="exampleInputPassword1">Gender</label>
                  <div class="radio ">
  <label style="width: 100px"><input type="radio" name="gender" value="Male"  <?php if($gender=='Male'){echo 'checked';} ?>>Male</label>
  <label style="width: 100px"><input type="radio" name="gender" value="Female" <?php if($gender=='Female'){echo 'checked';} ?>>Female</label>

</div>
                 
                </div>

                 <div class="form-group">
                  <label for="exampleInputPassword1">Email</label>
                  <input name="email" type="email" class="form-control" id="exampleInputPassword1"  required value=<?php echo "'".$email."'"; ?>>
                </div>



             
 <div class="form-group">
                <label>Department</label>
                <select class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true" name="classroom"><option >Select Department</option>
                 <?php
                  $sql = "SELECT * FROM department";
                  $result = $conn->query($sql);
                  if ($result->num_rows > 0) {
                   // output data of each row
                     while($row = $result->fetch_assoc()) {
                  echo "<option "; if(strcmp($classroom,$row["department_name"])){echo 'selected="selected"';} echo " value='".$row["department_name"]."' >".$row["department_name"]."</option>";
                       }
                        }
                  ?>
                </select>
                </div>


                <div class="form-group">
                 
                <label>Semester</label>
                <select name="parent" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true" >
                 <option value="0">Select Semester</option>

                      <?php

                  $sql = "SELECT * FROM semester";
                  $result = $conn->query($sql);

                  if ($result->num_rows > 0) {
                   // output data of each row
                     while($row = $result->fetch_assoc()) {
                      

                      echo "<option "; if(strcmp($parent,$row["semester_name"])){echo 'selected="selected"';} echo " value='".$row["semester_name"]."' >".$row["semester_name"]." </option>";
                       }
                                  }

                  ?>
                </select>
              
                </div>
 </div>
              <!-- /.box-body -->

              <div class="box-footer">
                <button type="submit" name="submit" value="submit" class="btn btn-primary">Update Student</button>
              </div>
            </form>

              <?php

              if (isset($_POST['submit'])) {
                $sid = $_POST['sid'];
                $fname = $_POST['fname'];
                $lname = $_POST['lname'];
                $classroom = $_POST['classroom'];
$email = $_POST['email'];
                $dob = date_format(new DateTime($_POST['dob']),'Y-m-d');
                //echo $dob;
                $gender = $_POST['gender'];
                  $address = $_POST['address'];
               
                $parent = $_POST['parent'];





                  try {

                    $sql = "UPDATE student set fname='".$fname."',lname='".$lname."',gender='".$gender."',semester_name='".$parent."',department_name='".$classroom."',email='".$email."' where sid='".$sid."'";
                   

                   // $sql = "INSERT INTO student (sid,fname,lname,bday,address,gender,parent,classroom) VALUES ('".$sid."', '".$fname."', '".$lname."','".$dob."','".$address."','".$gender."','".$parent."','".$classroom."')";

                  if ($conn->query($sql) === TRUE) {
                         echo "<script type='text/javascript'> var x = document.getElementById('truemsg');
x.style.display='block';</script>";
                      } else {
                            }
                    
                  } catch (Exception $e) {
                    
                  }





                  
                # code...
                                            }

              ?></div></div>

          
          <?php } ?>

          <div class="col-xs-14">


          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">All Cases</h3>
            </div>
            
            <!-- /.box-header -->
            <div class="box-body">
            
              <table id="example" class="display" >
                <thead>
                <tr>
                 
                  <th>License No</th>
                 <th>Vehicle Registration ID</th>
                   <th>NID</th>
                  <th>Status</th>
                  <th>Police ID</th>
                  <th>Division</th>
                  
               
                  
                </tr>
                </thead>
                <tbody>


                  <?php

                  $sql = "SELECT * FROM cases";
                  $result = $conn->query($sql);

                  if ($result->num_rows > 0) {
                   // output data of each row
                     while($row = $result->fetch_assoc()) {
                      echo "<tr><td> <a href='updatecases.php?update=". $row["id"]."'>" . $row["License_no"]."</a> </td><td><a href='updatecases.php?update=". $row["id"]."'>" . $row["Vehicle_number"]. "</a></td><td> <a href='updatecases.php?update=". $row["id"]."'>" . $row["NID_No"]. "</a> </td><td> <a href='updatecases.php?update=". $row["id"]."'>" . $row["Status"]. "</a> </td><td> <a href='updatecases.php?update=". $row["id"]."'>" . $row["Police_id"]. "</a></td><td><a href='updatecases.php?update=". $row["id"]."'>" . $row["address"]. "</a></td></tr>";
                       }
                                  }

                  ?>


                </tbody>
                <tfoot>
                 
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
            
          </div>
          <!-- /.box -->

          

        </div>

      <!--------------------------
        | Your Page Content Here |
        -------------------------->
   
    </section>

    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
 <?php include_once 'footer.php'; ?>
  
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- Select2 -->
<script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>


<script src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap color picker -->
<script src="bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="plugins/timepicker/bootstrap-timepicker.min.js"></script>

<script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="plugins/iCheck/icheck.min.js"></script>
<!-- FastClick -->
<script src="bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<!-- Page script -->

<script>
  $(function () {
    $('#example1').DataTable()
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
</script>


<script>   $('.select2').select2()
  $('#datepicker').datepicker({
      autoclose: true
    });


        
            var r = document.getElementById("new"); 
            r.className += "active"; 
           
    </script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>