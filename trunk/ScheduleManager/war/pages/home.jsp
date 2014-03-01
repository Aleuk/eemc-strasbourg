<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>


		
			<display:table id="blogTable" style="border-spacing: 0px 10px;" name="listeArticles"
				pagesize="7" requestURI="/controller/accueil/blog" class="table">
				<display:setProperty name="basic.msg.empty_list" value="" />
				<display:setProperty name="basic.show.header" value="false" />
				<display:setProperty name="paging.banner.onepage" value="" />
				<display:setProperty name="paging.banner.one_item_found" value="" />
				<display:setProperty name="paging.banner.some_items_found" value="" />
				<display:setProperty name="paging.banner.all_items_found" value="" />
				<display:column property="contenuString" />
			</display:table>