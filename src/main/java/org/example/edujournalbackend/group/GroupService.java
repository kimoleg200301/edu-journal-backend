package org.example.edujournalbackend.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupDao groupDao;

    @Autowired
    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }
    public Group findByGroupId(Long edu_group_id) {
        return groupDao.findByGroupId(edu_group_id);
    }
    public List<Group> findAllGroups() {
        return groupDao.findAllGroups();
    }
    public Boolean saveGroup(Group group) {
        return groupDao.save(group);
    }
    public Boolean updateGroup(Group group) {
        return groupDao.update(group);
    }
    public Boolean deleteGroup(Long edu_group_id) {
        return groupDao.delete(edu_group_id);
    }
}
