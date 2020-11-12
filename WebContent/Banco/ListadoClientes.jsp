<%@page import="dominio.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Banco UTN - Lista de cuentas</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Styles for the datatables -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>
<body id="page-top">
	<div id="wrapper">

		<!-- Sidebar -->
		<!-- INCLUYO EL MEN  AC  -->
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
						<div class="topbar-divider d-none d-sm-block"> </div>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow">
							<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
								<span class="mr-2 d-none d-lg-inline text-gray-600 small">Andr� Villalta</span> 
								<img class="img-profile rounded-circle"src="img/profile.png">
							</a> <!-- Dropdown - User Information -->
							<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> 
									<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a> 
								<a class="dropdown-item" href="#"> 
									<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> Settings									
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal"> 
									<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>Logout									
								</a>
							</div>
						</li>
					</ul>
				</nav>
				<!-- End of Topbar -->
					
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Listado de clientes</h6>
						</div>					
						<div class="card-body">
							<div class="table-responsive">
							
							<%
								List<User> listU = null;
								if (request.getAttribute("userList") != null){
									listU = (List<User>) request.getAttribute("userList");
								}
							%>	
								<table class="table table-bordered" id="dataTable" cellspacing="0">				
									<thead>
										<tr>
											<th>DNI</th>
											<th>CUIL</th>
                      						<th>Nombre completo</th>
                      						<th>Email</th>
                      						<th>Nacionalidad</th>
                      						<th>Fecha Nacimiento</th>
                      						<th>Sexo</th>
                      						<th>Ver m�s</th>
										</tr>
									</thead>
									<tbody>
									
									<% 
									if(listU != null)
									for (User u : listU ) { %>							
										<tr>
					                      <td><%=u.getDni()%></td>
					                      <td><%=u.getCuil()%></td>
					                      <td><%=u.getFirstName()%> + <%=u.getLastName() %></td>
					                      <td><%=u.getEmail()%></td>
					                      <td><%=u.getNacionality()%></td>
					                      <td><%=u.getBirthDate()%></td>
					                      <td><%=u.getGender()%></td>
					                      <td>
					                      	<a class="text-decoration-none m-2" href="#">
					                      		<i class="fas fa-phone" style="font-size: 25px;"></i>
					                      	</a>
					                      	<a class="text-decoration-none m-2" href="#">
					                      		<i class="fas fa-address-book" style="font-size: 25px;"></i>
					                      	</a>
					                      </td>
					                    </tr>
					                <% } %>
									</tbody>
								</table>
							</div>
						</div>
					</div>

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

	<!-- Page level plugins -->
	<script src="vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/datatables-demo.js"></script>
</body>
</html>