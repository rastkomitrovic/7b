package com.rastkomitrovic.mapper;

import com.rastkomitrovic.entity.HashTag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HashTagMapper {

    public List<String> mapListToDto(Set<HashTag> hashTags) {
        return hashTags.stream().map(HashTag::getHashTagName).collect(Collectors.toList());
    }
}
