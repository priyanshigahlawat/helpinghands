package com.example.HelpingHands.request;

import lombok.Data;
import java.util.*;

@Data
public class MyInboxItems {
    String name;
    String email;
    Long phone;
    Long request_id;
    Long donor_id;
    Long item_id;
    String item_name;
    String item_description;
    Date date;
}