<%@page import="dominio.User"%>
<%@page import="dominio.Nationality"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%
    User userLogin = new User(); 
    userLogin = (User)session.getAttribute("userLogin");
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>Banco UTN - cliente <%= request.getParameter("p") %></title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<script type="text/javascript">
	function BuscarExistencia(){
		alert("El DNI ya está registrado enla base de datos");
	}
</script>

</head>
<body id="page-top">
	<div id="wrapper">
		<!-- Sidebar -->
		<!-- INCLUYO EL MENÚ ACÁ -->
		<jsp:include page="MenuAdmin.html"></jsp:include> 
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
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
								<span class="mr-2 d-none d-lg-inline text-gray-600 small">${userLogin.getFirstName()} ${userLogin.getLastName()}</span> 
								<img class="img-profile rounded-circle" src="img/profile.png">
							</a> 
							<!-- Dropdown - User Information -->
							<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
								<a class="collapse-item" href="ServletLogin?btnLogout=1"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i><span>Cerrar sesión</span> </a>
							</div>
						</li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Baja cliente</h1>
					</div>
					
					<form method="get" action="ServletClientes" enctype="multipart/form-data">
						<div class="form-row">
							<div class="form-group col-md-4">
								<label for="title">DNI</label> 
								<input class="form-control" name = "textDni" required type="text" placeholder="Ingrese DNI" maxlength="9">           				
							</div>
							<div class="form-group col-md-2 mt-1">
            					<button type="submit" name="btnBuscar" class="btn btn-warning btn-circle mt-4"><i class="fas fa-search"></i></button>
							</div>
						</div>
					</form>
					<% if( request.getAttribute("usrBaja") != null ) 
					{
					    User usrBaja = new User();
					    usrBaja = (User)request.getAttribute("usrBaja");
					    %>
					    <form method="get" action="ServletClientes" enctype="multipart/form-data">
							<div class="form-row">
								<div class="form-group col-md-4">
									<label for="title">DNI</label> 
									<input class="form-control" name = "textDni" required type="text" placeholder="Ingrese DNI" maxlength="9" readonly value="<%=usrBaja.getDni() %>">           				
								</div>
								<div class="form-group col-md-4">
									<label for="title">CUIL</label> 
									<input class="form-control" name = "textCuil" required type="text" placeholder="Ingrese CUIL" maxlength="11" readonly value="<%=usrBaja.getCuil() %>">
								</div>
								<div class="form-group col-md-6">
									<label for="title">Nombre</label> 
									<input class="form-control" name = "textNombre" required type="text" placeholder="Ingrese Nombre" maxlength="45" readonly value="<%=usrBaja.getFirstName() %>">
								</div>
								<div class="form-group col-md-6">
									<label for="title">Apellido</label> 
									<input class="form-control" name = "textApellido" required type="text" placeholder="Ingrese Apellido" maxlength="45" readonly value="<%=usrBaja.getLastName() %>">
								</div>
							</div>
							
							<div class="modal fade" id="modalAltaUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						    <div class="modal-dialog" role="document">
						      <div class="modal-content">
						        <div class="modal-header">
						          <h5 class="modal-title" id="exampleModalLabel">Eliminar cliente?</h5>
						          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
						            <span aria-hidden="true">×</span>
						          </button>
						        </div>
						        <div class="modal-body">Realmente querés eliminar el cliente?</div>
						        <div class="modal-footer">
						          <input type="submit" name="btnEliminarCliente" class="btn btn-primary mt-2" value="Eliminar cliente">
						        </div>
						      </div>
						    </div>
						  </div>
						  <a class="btn btn-primary" href="#" data-toggle="modal" data-target="#modalAltaUsuario">
			                  Eliminar cliente
			                </a>
						</form> 
				<%
					}
				%>

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