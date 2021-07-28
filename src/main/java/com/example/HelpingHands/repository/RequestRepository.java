package com.example.HelpingHands.repository;

import com.example.HelpingHands.entity.DonateEntity;
import com.example.HelpingHands.entity.InboxRecord;
import com.example.HelpingHands.entity.RequestEntity;
import com.example.HelpingHands.entity.UserEntity;
import com.example.HelpingHands.request.MyInboxItems;
import com.example.HelpingHands.response.PortalResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity,Long> {

    @Query(value = "SELECT user.name as name, user.email as email, user.phone as phone, request.reqid as request_id, request.donorid as donor_id, donate.itemid as item_id, donate.aprroved_status, donate.expire_status, donate.item_name as item_name, donate.item_desc as item_description, donate.date as date FROM user, request, donate WHERE user.userid = request.reqid AND request.donorid = :userid AND request.itemid = donate.itemid", nativeQuery = true)
    public List<Object[]> fetchInboxRecord(Long userid);

    @Query(value = "SELECT user.name as name, user.email as email, user.phone as phone, request.reqid as request_id, request.donorid as donor_id, donate.itemid as item_id, donate.aprroved_status, donate.expire_status, donate.item_name as item_name, donate.item_desc as item_description, donate.date as date FROM user, request, donate WHERE user.userid = request.donorid AND request.reqid = :userid AND request.itemid = donate.itemid", nativeQuery = true)
    public List<Object[]> fetchOutboxRecord(Long userid);

    @Query(value = "SELECT count( * ) FROM request where reqid = :reqid and itemid = :itemid", nativeQuery = true)
    public Long getItemsRequested(Long reqid, Long itemid);

    @Query(value = "SELECT * FROM request where request.donorid = :donorid", nativeQuery = true)
    public List<RequestEntity> listOfRequests(Long donorid);

    @Query(value = "SELECT * FROM request where request.reqid = :donorid", nativeQuery = true)
    public List<RequestEntity> listOfRequests2(Long donorid);
}
