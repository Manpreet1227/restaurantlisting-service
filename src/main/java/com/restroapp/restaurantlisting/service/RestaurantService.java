package com.restroapp.restaurantlisting.service;

import com.restroapp.restaurantlisting.dto.RestaurantDTO;
import com.restroapp.restaurantlisting.entity.Restaurant;
import com.restroapp.restaurantlisting.mapper.RestaurantMapper;
import com.restroapp.restaurantlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    // Fetch all restaurants
    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants.stream()
                .map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDTO)
                .collect(Collectors.toList());
    }

    // Add a new restaurant
    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        Restaurant savedRestaurant = restaurantRepo.save(
                RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO)
        );
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
    }

    // Fetch restaurant by ID
    public Optional<RestaurantDTO> fetchRestaurantById(Integer id) {
        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
        return restaurant.map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDTO);
    }
}