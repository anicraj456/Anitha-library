package org.launchcode.anithalibrary.data;

import org.launchcode.anithalibrary.model.Bookinfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookinfoRepository extends CrudRepository<Bookinfo, Integer>  {
}