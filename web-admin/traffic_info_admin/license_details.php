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
  $update = "SELECT * FROM driving_license WHERE id='".$_GET['update']."'";
  $result = $conn->query($update);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
                 $id = $row['id'];
                $ref_no = $row['ref_no'];
                $type_of_app = $row['type_of_app'];
                $dctb_serial = $row['dctb_serial'];
                $type_of_license = $row['type_of_license'];
                $velicle_class = $row['velicle_class'];
                $NID_No = $row['NID_No'];
                $name= $row['name'];
                $fathers_name=$row['fathers_name'];
                $mothers_name=$row['mothers_name'];
                $spouse_name=$row['spouse_name'];
                $address=$row['address'];
                $ref_date=$row['ref_date'];
               
                

                
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
  <title>License Details</title><link rel="icon" href="../img/favicon2.png">
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
        License Details
        <small>License Details</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> License</a></li>
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
                 License Details  
              </div>
<!-- general form elements -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">License</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form" method="POST" >
              <div class="box-body">

                 <div class="form-group">
                  <label for="exampleInputPassword1">Reference No</label>
                  <input name="id" type="text" class="form-control" id="exampleInputPassword1" readonly required value=<?php echo "'".$ref_no."'"; ?>>
                </div>

                <div class="form-group">
                  <label for="exampleInputPassword1">Type</label>
                  <input name="fname" type="text" class="form-control" id="exampleInputPassword1" required readonly value=<?php echo "'".$type_of_app."'"; ?> >
                </div>

                <div class="form-group">
                  <label for="exampleInputPassword1">DCTB Serial</label>
                  <input name="lname" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$dctb_serial."'"; ?>>
                </div>

             
          

                 <div class="form-group">
                  <label for="exampleInputPassword1">Types of License </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$type_of_license."'"; ?>>
                </div>

 <div class="form-group">
                  <label for="exampleInputPassword1">Vehicle Class </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$velicle_class."'"; ?>>
                </div>
                 
                 <div class="form-group">
                  <label for="exampleInputPassword1">NID No </label>
                  <input name="u_fine" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$NID_No."'"; ?>>
                </div>
                
                 <div class="form-group">
                  <label for="exampleInputPassword1"> Name</label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$name."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1"> Father Name </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$fathers_name."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Mother Name </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$mothers_name."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Spouse Name </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$spouse_name."'"; ?>>
                </div>
                 <div class="form-group">
                  <label for="exampleInputPassword1">Address </label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$address."'"; ?>>
                </div>
                
                 <div class="form-group">
                  <label for="exampleInputPassword1">Ref. Date</label>
                  <input name="text" type="text" class="form-control" id="exampleInputPassword1"  required readonly value=<?php echo "'".$ref_date."'"; ?>>
                </div>
              
                
                


                
 </div>
              <!-- /.box-body -->

              <div class="box-footer">
                <!-- <button type="hi" name="submit" value="submit" class="btn btn-primary">Update License Details</button> -->
              </div>
            </form>

              <?php

              if (isset($_POST['submit'])) {
                $u_id = $_POST['u_id'];
            
                $status = $_POST['status'];
               
   try {

                    $sql = "UPDATE License Details set status='".$status."' where id='".$u_id."'";
                   

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