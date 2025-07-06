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
  $update = "SELECT * FROM vehicle_registration_brta WHERE id='".$_GET['update']."'";
  $result = $conn->query($update);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
                 $id = $row['id'];
                $Registration_No = $row['Registration_No'];
                $Vehicle_Description = $row['Vehicle_Description'];
                $Vehicle_class = $row['Vehicle_class'];
                $CC = $row['CC'];
                $Hire = $row['Hire'];
                $Color = $row['Color'];
                $Fuel= $row['Fuel'];
                $Seat=$row['Seat'];
                $Engine_No=$row['Engine_No'];
                $Chassis_No=$row['Chassis_No'];
                $Date=$row['Date'];
                $Wheel_Base=$row['Wheel_Base'];
                $Issuing_Authority=$row['Issuing_Authority'];
                $Weight_Unladen=$row['Weight_Unladen'];
                $Weight_laden=$row['Weight_laden'];
                $NID_No=$row['NID_No'];
                $photo=$row['photo'];
                $Owner_name=$row['Owner_name'];
                $Owner_address=$row['Owner_address'];
                $Tyre_size=$row['Tyre_size'];
                $HP=$row['H.P'];
                $Mfg_Year=$row['Mfg_Year'];
                

                
    }
}
}

?>


<!DOCTYPE html>

<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Vehicle Details</title><link rel="icon" href="../img/favicon2.png">
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">

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
        Vehicle Details
        <small>Vehicle Details</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Vehicle</a></li>
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
                 $text = $_POST['text'];
                $classroom = $_POST['classroom'];
 $password = $_POST['password'];
          
                //echo $dob;
                $gender = $_POST['gender'];
      
                $parent=" ";
                if(isset($_POST['parent'])){
                $parent = $_POST['parent'];}





                  try {


                   

                    $sql = "INSERT INTO student (sid,fname,lname,gender,semester_name,department_name,text) VALUES ('".$sid."', '".$fname."', '".$lname."','".$gender."','".$parent."','".$classroom."','".$text."')";

                  if ($conn->query($sql) === TRUE) {
                            $sql1 = "INSERT INTO user (role,text,password) VALUES ('student', '".$text."','".$password."')";
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
     <div class="col-xs-10">

   

         <div class="alert alert-success alert-dismissible" style="display: none;" id="truemsg">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-check"></i> Success!</h4>
                Update Vehicle Details Successfully 
              </div>
<!-- general form elements -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Update Case</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form" method="POST" >
              <div class="box-body">

                 <div class="form-group">
                  <label for="exampleInputPassword1">ID</label>
                  <input name="id" type="text" class="form-control" id="exampleInputPassword1" readonly required value=<?php echo "'".$id."'"; ?>>
                </div>

                <div class="form-group">
                  <label for="exampleInputPassword1">Registration No</label>
                  <input name="fname" type="text" class="form-control" id="exampleInputPassword1" required readonly value=<?php echo "'".$Registration_No."'"; ?> >
                </div>

                <div class="form-group">
                  <label for="exampleInputPassword1">Vehicle Description</label>
                  <input name="lname" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Vehicle_Description."'"; ?>>
                </div>

             
          

                 <div class="form-group">
                  <label for="exampleInputPassword1">CC </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$CC."'"; ?>>
                </div>

 <div class="form-group">
                  <label for="exampleInputPassword1">Color </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Color."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Fuel </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Fuel."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Seat </label>
                  <input name="u_fine" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Seat."'"; ?>>
                </div>
                
                 <div class="form-group">
                  <label for="exampleInputPassword1"> Engine No</label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Engine_No."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1"> Chassis No </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Chassis_No."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Hire </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Hire."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Date </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Date."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Wheel Base </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Wheel_Base."'"; ?>>
                </div>
                
                 <div class="form-group">
                  <label for="exampleInputPassword1">Issuing Authority </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Issuing_Authority."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Weight unladen </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Weight_Unladen."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Weight Laden </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Weight_laden."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">NID No </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$NID_No."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Owner Name </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Owner_name."'"; ?>>
                </div>
                
                 <div class="form-group">
                  <label for="exampleInputPassword1">Owner Address </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Owner_address."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Tyre Size</label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Tyre_size."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">H.P. </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$HP."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Mfg Year </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$Mfg_Year."'"; ?>>
                </div>
                
                


                
 </div>
              <!-- /.box-body -->

              <div class="box-footer">
                <!-- <button type="hi" name="submit" value="submit" class="btn btn-primary">Update Vehicle Details</button> -->
              </div>
            </form>

              <?php

              if (isset($_POST['submit'])) {
                $u_id = $_POST['u_id'];
            
                $status = $_POST['status'];
               
   try {

                    $sql = "UPDATE Vehicle Details set status='".$status."' where id='".$u_id."'";
                   

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