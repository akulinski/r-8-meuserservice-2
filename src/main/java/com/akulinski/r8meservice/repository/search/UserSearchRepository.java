package com.akulinski.r8meservice.repository.search;

import com.akulinski.r8meservice.domain.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends ElasticsearchRepository<User, Long> {

    @Query(
        "{" +
            "        \"fuzzy\": {\n" +
            "            \"login\": {\n" +
            "                \"value\": \"?0\",\n" +
            "                \"fuzziness\": \"AUTO\",\n" +
            "                \"max_expansions\": 50,\n" +
            "                \"prefix_length\": 0,\n" +
            "                \"transpositions\": true,\n" +
            "                \"rewrite\": \"constant_score\"\n" +
            "            }\n" +
            "        }\n" +
            "}"
    )
    List<User> findByLoginFuzzy(String login);

    @Query(
        "{" +
            "        \"regexp\": {" +
            "            \"login\": {" +
            "                \"value\": \".*?0.*\"," +
            "                \"flags\" : \"ALL\"," +
            "                \"max_determinized_states\": 10000," +
            "                \"rewrite\": \"constant_score\"" +
            "            }" +
            "        }" +
            "}"
    )
    List<User> findByRegex(String regex);
}
