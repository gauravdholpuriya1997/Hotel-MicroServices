package com.lcws.hotel.repositories;

import com.lcws.hotel.emtites.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
