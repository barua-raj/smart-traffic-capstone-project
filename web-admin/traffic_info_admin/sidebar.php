<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
     

      <!-- search form (Optional) -->
     
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header"> Dashboard</li>
        <!-- Optionally, you can add icons to the links -->
        <!-- <li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>
        <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>-->
        <li id="stat"><a href="./"><i class="fa fa-bar-chart-o"></i> <span>Statistics</span> </a></li>

        <?php if($_SESSION['role']=='admin'){ ?>
        <li id="new"><a href="./checkcases.php"><i class="fa fa-users"></i> <span>Check Cases</span> </a></li>
         <li id="new"><a href="./vehicle_list.php"><i class="fa fa-car"></i> <span>Vehicle Details</span> </a></li>
      <li id="new"><a href="./license_list.php"><i class="fa fa-id-card"></i> <span>License Details</span> </a></li>
      
  
      <?php }
      ?>
      


      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>