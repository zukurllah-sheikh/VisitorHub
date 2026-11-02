package com.project.VisitorHub.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.VisitorHub.model.*;
public interface VisitorRepo extends JpaRepository<Visitor,Long>{
	
}
