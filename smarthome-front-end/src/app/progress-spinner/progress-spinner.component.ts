import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";

@Component({
  selector: 'app-progress-spinner',
  templateUrl: './progress-spinner.component.html',
  styleUrls: ['./progress-spinner.component.css']
})
export class ProgressSpinnerComponent implements OnInit {

  constructor(
    private dialogRef: MatDialogRef<ProgressSpinnerComponent>,
    @Inject(MAT_DIALOG_DATA) data) {

  }

  ngOnInit() {
    setTimeout(() => {
      this.dialogRef.close();
    }, 1000);
  }

}
