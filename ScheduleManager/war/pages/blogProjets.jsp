<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>


		
			<display:table id="dataTable" name="listeArticles"
				pagesize="3" requestURI="/controller/photo/blog" class="table table-striped table-bordered table-hover">
				<display:setProperty name="basic.msg.empty_list" value="" />
				<display:setProperty name="basic.show.header" value="false" />
				<display:setProperty name="paging.banner.onepage" value="" />
				<display:setProperty name="paging.banner.one_item_found" value="" />
				<display:column property="contenuString" />
			</display:table>