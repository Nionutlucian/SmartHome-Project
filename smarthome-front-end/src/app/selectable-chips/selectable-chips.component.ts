import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';

@Component({
  selector: 'app-selectable-chips',
  templateUrl: './selectable-chips.component.html',
  styleUrls: [ './selectable-chips.component.css' ]
})
export class SelectableChipsComponent implements OnInit {
  @Input() chips: string[] = [];
  @Input() initialChips: string[];
  @Output() onChipSelected = new EventEmitter<string[]>();

  private chipCollection: Map<string, boolean> = new Map<string, boolean>();

  ngOnInit(): void {
    for (const chip of this.chips) {
      this.chipCollection.set(chip, false);
    }

    if (this.initialChips) {
      for (const chip of this.initialChips) {
        this.chipCollection.set(chip, true);
      }
    }

    this.onChipSelected.emit([]);
  }

  isSelected(chip: any) {
    return this.chipCollection.get(chip);
  }

  onClick($event: Event, chip: string) {
    $event.preventDefault();
    $event.stopPropagation();
    this.selectChip(chip);

    const selected = this.getSelected();
    this.onChipSelected.emit(selected);
  }

  selectChip(chip: string) {
    if (this.chipCollection.get(chip)) {
      this.chipCollection.set(chip, false);
    } else {
      this.chipCollection.set(chip, true);
    }
  }

  getSelected() {
    const selected = <string[]>[];

    Array.from(this.chipCollection.entries(), (v: [ string, boolean ]) => {
      if (v[ '1' ]) {
        selected.push(v[ '0' ]);
      }
    });

    return selected; 
  }
}
