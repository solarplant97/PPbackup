<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page trimDirectiveWhitespaces="true"%>
<div class="container">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#navbar" aria-expanded="false"
			aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="/">My Project</a>
	</div>
	<div id="navbar" class="collapse navbar-collapse">
		<ul class="nav navbar-nav">
			<li><a href="/">Home</a></li>
			<li><a href="/board/boardList.do">게시판</a></li>
			<li><a href="/gallery/galleryList.do">갤러리</a></li>
			<li><a href="/member/login.do">로그인</a></li>			
			<%-- dropdown 메뉴 추가시 다시 활성화 
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#">Action</a></li>
					<li><a href="#">Another action</a></li>
					<li><a href="#">Something else here</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Nav header</li>
					<li><a href="#">Separated link</a></li>
					<li><a href="#">One more separated link</a></li>
				</ul>
			</li> --%>
		</ul>
		
		<%-- <c:if test="${login.userId != null and login.userId != ''}">
		<form class="navbar-form navbar-right">
            <!-- <div class="form-group">
              <input type="text" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" placeholder="Password" class="form-control">
            </div> -->
            <p class="form-control-static">[ ${login.userName} ]님 반갑습니다</p>
            <button type="button" class="btn btn-success" onclick="location.href='/member/logout.do'">로그아웃</button>
            <!-- <button type="button" class="btn btn-success" onclick="location.href='/member/logout.do'">myPage</button> -->
          </form>
          </c:if> --%>
	</div>
	<!--/.nav-collapse -->
</div>