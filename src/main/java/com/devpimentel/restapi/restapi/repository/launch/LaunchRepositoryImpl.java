package com.devpimentel.restapi.restapi.repository.launch;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.devpimentel.restapi.restapi.model.Category_;
import com.devpimentel.restapi.restapi.model.Person_;
import com.devpimentel.restapi.restapi.repository.projection.ReleaseSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.devpimentel.restapi.restapi.model.Launch;
import com.devpimentel.restapi.restapi.model.Launch_;
import com.devpimentel.restapi.restapi.repository.filter.LaunchFilter;

public class LaunchRepositoryImpl implements LaunchRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Launch> filter(LaunchFilter launchFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Launch> criteria = builder.createQuery(Launch.class);
        Root<Launch> root = criteria.from(Launch.class);

        // restrições
        Predicate[] predicates = createRestrictions(launchFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Launch> query = manager.createQuery(criteria);
        addQueryConstraintPagination(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(launchFilter));
    }

    @Override
    public Page<ReleaseSummary> sumUp(LaunchFilter launchFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ReleaseSummary> criteria = builder.createQuery(ReleaseSummary.class);
        Root<Launch> root = criteria.from(Launch.class);

        criteria.select(builder.construct(ReleaseSummary.class
                , root.get(Launch_.id), root.get(Launch_.description)
                , root.get(Launch_.dueDate), root.get(Launch_.payday)
                , root.get(Launch_.value), root.get(Launch_.type)
                , root.get(Launch_.category).get(Category_.name)
                , root.get(Launch_.person).get(Person_.name)));

        Predicate[] predicates = createRestrictions(launchFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<ReleaseSummary> query = manager.createQuery(criteria);
        addQueryConstraintPagination(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(launchFilter));
    }

    private Predicate[] createRestrictions(LaunchFilter launchFilter, CriteriaBuilder builder, Root<Launch> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(launchFilter.getDescription())) {
            predicates.add(builder.like(builder.lower(root.get(Launch_.description)),"%" + launchFilter.getDescription().toLowerCase() + "%"));
        }

        if (launchFilter.getExpirationDateOf() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get(Launch_.dueDate), launchFilter.getExpirationDateOf()));
        }

        if (launchFilter.getExpirationDateTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get(Launch_.dueDate), launchFilter.getExpirationDateTo()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addQueryConstraintPagination(TypedQuery<?> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRegistration = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPageRegistration);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Long total(LaunchFilter launchFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Long.class);
        Root<Launch> root = criteria.from(Launch.class);

        Predicate[] predicates = createRestrictions(launchFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return (Long) manager.createQuery(criteria).getSingleResult();
    }
}
