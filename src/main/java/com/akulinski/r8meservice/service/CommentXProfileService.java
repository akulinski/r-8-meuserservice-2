package com.akulinski.r8meservice.service;

import com.akulinski.r8meservice.domain.CommentXProfile;
import com.akulinski.r8meservice.repository.CommentXProfileRepository;
import com.akulinski.r8meservice.service.dto.CommentXProfileDTO;
import com.akulinski.r8meservice.service.mapper.CommentXProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link CommentXProfile}.
 */
@Service
@Transactional
public class CommentXProfileService {

    private final Logger log = LoggerFactory.getLogger(CommentXProfileService.class);

    private final CommentXProfileRepository commentXProfileRepository;

    private final CommentXProfileMapper commentXProfileMapper;


    public CommentXProfileService(CommentXProfileRepository commentXProfileRepository, CommentXProfileMapper commentXProfileMapper) {
        this.commentXProfileRepository = commentXProfileRepository;
        this.commentXProfileMapper = commentXProfileMapper;
    }

    /**
     * Save a commentXProfile.
     *
     * @param commentXProfileDTO the entity to save.
     * @return the persisted entity.
     */
    public CommentXProfileDTO save(CommentXProfileDTO commentXProfileDTO) {
        log.debug("Request to save CommentXProfile : {}", commentXProfileDTO);
        CommentXProfile commentXProfile = commentXProfileMapper.toEntity(commentXProfileDTO);
        commentXProfile = commentXProfileRepository.save(commentXProfile);
        CommentXProfileDTO result = commentXProfileMapper.toDto(commentXProfile);
        return result;
    }

    /**
     * Get all the commentXProfiles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CommentXProfileDTO> findAll() {
        log.debug("Request to get all CommentXProfiles");
        return commentXProfileRepository.findAll().stream()
            .map(commentXProfileMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


  /*  *//**
     * Get one commentXProfile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     *//*
    @Transactional(readOnly = true)
    public Optional<CommentXProfileDTO> findOne(Long id) {
        log.debug("Request to get CommentXProfile : {}", id);
        return commentXProfileRepository.findById(id)
            .map(commentXProfileMapper::toDto);
    }*/

  /*  *//**
     * Delete the commentXProfile by id.
     *
     * @param id the id of the entity.
     *//*
    public void delete(Long id) {
        log.debug("Request to delete CommentXProfile : {}", id);
        commentXProfileRepository.deleteById(id);
    }
*/
}
