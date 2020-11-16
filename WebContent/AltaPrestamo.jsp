<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Banco UTN - Alta pr�stamo</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>
<body id="page-top">
	<div id="wrapper">

		<!-- Sidebar -->
		<!-- INCLUYO EL MEN� AC� -->
		<jsp:include page="MenuCliente.html"></jsp:include> 
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">
						<div class="topbar-divider d-none d-sm-block"></div>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow">
							<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
								<span class="mr-2 d-none d-lg-inline text-gray-600 small">Andr� Villalta</span> 
								<img class="img-profile rounded-circle" src="img/profile.png">
							</a> 
							<!-- Dropdown - User Information -->
							<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> 
									<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a> 
								<a class="dropdown-item" href="#"> 
									<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									Settings
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal"> 
									<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div>
						</li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Alta de pr�stamo</h1>
					</div>
					
					<form method="post" action="ServletPrestamos">
						<div class="form-row">
							<div class="form-group col-md-3">
								<label>N° de Cuenta</label>
								
				<!-------------------ToDo---------------------------------------------------------------------->
				<!--Cuando se tenga los datos del User en session, completar un dropdown con sus cuentas -->
				<!----------------------------------------------------------------------------------------->
				
								<input name="txtAccountNumber" class="form-control" required="required" type="text" placeholder="Ingrese N° de Cuenta"> 
							</div>					
						</div>
						<div class="form-row">
							<div class="form-group col-md-3">
								<label for="title">Cantidad</label>
								<input name="txtAmountReqByCustomer" class="form-control" required="required" type="text" placeholder="Ingrese cantidad">        				
							</div>	
							<div class="form-group col-md-3">
								<label>Cuotas</label>
								<select name="comboAmountOfFees" class="custom-select">
									<option value="3">3</option>
									<option value="6">6</option>
									<option value="12">12</option>
									<option value="24">24</option>
								</select>     
							</div>		
						</div>

						<button name="btnPedirPrestamo" type="submit" class="btn btn-primary mt-2">Pedir Préstamo</button>
					</form>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Banco UTN 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>

	<!-- End of Page Wrapper -->

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
</body>
</html>