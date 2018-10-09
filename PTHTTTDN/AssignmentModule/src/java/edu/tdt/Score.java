/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "score", catalog = "Assignment", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s")
    , @NamedQuery(name = "Score.findByStudentId", query = "SELECT s FROM Score s WHERE s.scorePK.studentId = :studentId")
    , @NamedQuery(name = "Score.findBySubjectId", query = "SELECT s FROM Score s WHERE s.scorePK.subjectId = :subjectId")
    , @NamedQuery(name = "Score.findByScoreType", query = "SELECT s FROM Score s WHERE s.scorePK.scoreType = :scoreType")
    , @NamedQuery(name = "Score.findByScore", query = "SELECT s FROM Score s WHERE s.score = :score")})
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ScorePK scorePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score", nullable = false)
    private float score;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subject subject;

    public Score() {
    }

    public Score(ScorePK scorePK) {
        this.scorePK = scorePK;
    }

    public Score(ScorePK scorePK, float score) {
        this.scorePK = scorePK;
        this.score = score;
    }

    public Score(String studentId, String subjectId, int scoreType) {
        this.scorePK = new ScorePK(studentId, subjectId, scoreType);
    }

    public ScorePK getScorePK() {
        return scorePK;
    }

    public void setScorePK(ScorePK scorePK) {
        this.scorePK = scorePK;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scorePK != null ? scorePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Score)) {
            return false;
        }
        Score other = (Score) object;
        if ((this.scorePK == null && other.scorePK != null) || (this.scorePK != null && !this.scorePK.equals(other.scorePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.tdt.Score[ scorePK=" + scorePK + " ]";
    }
    
}
