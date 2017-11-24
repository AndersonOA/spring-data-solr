package br.com.makersweb.solr.repository;

import br.com.makersweb.solr.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.*;

import java.util.List;

/**
 * @author anderson.aristides
 */
public interface BookRepository extends SolrCrudRepository<Book, String> {

    List<Book> findByName(String name);

    Page<Book> findByNameOrDescription(@Boost(2) String name, String description, Pageable pageable);

    @Query("name:?0")
    @Facet(fields = { "categories_txt" }, limit = 5)
    FacetPage<Book> findByNameAndFacetOnCategories(String name, Pageable page);

    @Highlight(prefix = "<highlight>", postfix = "</highlight>")
    HighlightPage<Book> findByDescription(String description, Pageable pageable);

}
