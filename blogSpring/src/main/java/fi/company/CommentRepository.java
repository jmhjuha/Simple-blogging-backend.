package fi.company;

import org.springframework.data.repository.CrudRepository;

interface CommentRepository extends CrudRepository<Comment, Long> {

}
