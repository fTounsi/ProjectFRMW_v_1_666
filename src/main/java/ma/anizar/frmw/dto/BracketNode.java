package ma.anizar.frmw.dto;

import lombok.Data;
import ma.anizar.frmw.model.Member;

@Data
public class BracketNode {
    private Member participant;
    private BracketNode left, right;

    public BracketNode(Member participant) {
        this.participant = participant;
        left = null;
        right = null;
    }
}