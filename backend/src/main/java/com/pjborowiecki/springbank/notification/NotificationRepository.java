package com.pjborowiecki.springbank.notification;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    @Query(value = "FROM Notification n WHERE CURDATE() BETWEEN notificanStartDate and notificationEndDate")
    List<Notification> findAllActiveNotifications();

}
