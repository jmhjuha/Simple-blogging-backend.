package fi.company;

import org.springframework.data.repository.CrudRepository;

interface BlogRepository extends CrudRepository<Blog, Long> {
}
