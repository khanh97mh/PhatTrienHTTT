/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Embeddable
public class ScorePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "student_id", nullable = false, length = 2147483647)
    private String studentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "subject_id", nullable = false, length = 2147483647)
    private String subjectId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score_type", nullable = false)
    private int scoreType;

    public ScorePK() {
    }

    public ScorePK(String studentId, String subjectId, int scoreType) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.scoreType = scoreType;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public int getScoreType() {
        return scoreType;
    }

    public void setScoreType(int scoreType) {
        this.scoreType = scoreType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        hash += (subjectId != null ? subjectId.hashCode() : 0);
        hash += (int) scoreType;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScorePK)) {
            return false;
        }
        ScorePK other = (ScorePK) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        if ((this.subjectId == null && other.subjectId != null) || (this.subjectId != null && !this.subjectId.equals(other.subjectId))) {
            return false;
        }
        if (this.scoreType != other.scoreType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.tdt.ScorePK[ studentId=" + studentId + ", subjectId=" + subjectId + ", scoreType=" + scoreType + " ]";
    }
    
}
