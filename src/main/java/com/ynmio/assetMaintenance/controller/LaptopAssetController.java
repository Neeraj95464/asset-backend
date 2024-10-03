package com.ynmio.assetMaintenance.controller;

import com.ynmio.assetMaintenance.model.LaptopAsset;
import com.ynmio.assetMaintenance.repository.LaptopAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class LaptopAssetController {
    @Autowired
    private LaptopAssetRepository laptopAssetRepository;

    @GetMapping("/laptop_asset")
    public List<LaptopAsset> getAll(){
        return laptopAssetRepository.findAll();
    }
    @GetMapping("/laptop_asset/{id}")
    public Optional<LaptopAsset> getById(@PathVariable Integer id) {
        return laptopAssetRepository.findById(id);
    }
    @GetMapping("/asset_count")
    public Map<String, Long> assetCount() {
        Map<String, Long> assetCounts = new HashMap<>();

        assetCounts.put("Laptop", laptopAssetRepository.count());
        assetCounts.put("Desktop", laptopAssetRepository.count());
//        assetCounts.put("Switch", laptopAssetRepository.countByType("Switch"));
//        assetCounts.put("Router", laptopAssetRepository.countByType("Router"));

        return assetCounts;
    }

    @GetMapping("/server_test")
    public String test(){
        return "Your server running correctly";
    }
    @PostMapping("/add_laptop_asset")
    public String addLaptop(@RequestBody LaptopAsset asset){
        System.out.println("user data "+asset.getLaptopName()+asset.getDescription()
        +asset.getPrice()+asset.getSerialNumber());
        laptopAssetRepository.save(asset);
        return "addition success";
    }
}
