package com.vhgomes.easytweet.dtos;

import com.vhgomes.easytweet.models.Tweet;

import java.util.List;

public record FeedDTO(List<FeedItemDTO> feedItens,
                      int page,
                      int pageSize,
                      int totalPages,
                      Long totalElements
) {
}
